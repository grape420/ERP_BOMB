package com.greedy.erp_bomb.ea.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.greedy.erp_bomb.ea.model.dto.AddendumDTO;
import com.greedy.erp_bomb.ea.model.dto.DocumentFormDTO;
import com.greedy.erp_bomb.ea.model.dto.EACarbonDTO;
import com.greedy.erp_bomb.ea.model.dto.EADTO;
import com.greedy.erp_bomb.ea.model.dto.EAPathDTO;
import com.greedy.erp_bomb.ea.model.dto.EAPathPk;
import com.greedy.erp_bomb.member.model.dto.MemberDTO;

@Repository
public class EaDAO {
	
	@PersistenceContext
	private EntityManager em;

	public List<EADTO> findMyEa(String userName) {
		String jpql = "SELECT a FROM EADTO as a WHERE a.member.name = :name";
		
		List<EADTO> myEaList = em.createQuery(jpql, EADTO.class).setParameter("name", userName).getResultList();
		
		for(EADTO ea : myEaList) {
			ea.getAddendumList().size();
			ea.getEaApprovalPathList().size();
			ea.getEaCarbonList().size();
		}
		
		return myEaList;
	}

	public List<EADTO> findEaPathList(String userName) {
		String jpql = "SELECT a FROM EADTO as a JOIN a.eaApprovalPathList as b WHERE b.member.name = :name AND NOT a.eaStatus = 4";
		
		List<EADTO> eaPathList = em.createQuery(jpql, EADTO.class).setParameter("name", userName).getResultList();
		
		for(EADTO eaPath : eaPathList) {
			eaPath.getAddendumList().size();
			eaPath.getEaApprovalPathList().size();
			eaPath.getEaCarbonList().size();
		}
		
		return eaPathList;
	}

	public List<EADTO> findEaCarbonList(String userName) {
		String jpql = "SELECT a FROM EADTO as a JOIN a.eaCarbonList as b WHERE b.member.name = :name AND NOT a.eaStatus = 4";
		
		List<EADTO> eaCarbonList = em.createQuery(jpql, EADTO.class).setParameter("name", userName).getResultList();
		
		for(EADTO eaCarbon : eaCarbonList) {
			eaCarbon.getAddendumList().size();
			eaCarbon.getEaApprovalPathList().size();
			eaCarbon.getEaCarbonList().size();
		}
		return eaCarbonList;
	}

	public List<MemberDTO> findMemberList() {
		String jpql = "SELECT a FROM MemberDTO as a ORDER BY a.rank.no DESC";
		return em.createQuery(jpql, MemberDTO.class).getResultList();
	}

	public void insertEa(EADTO ea) {
		ea.setMember(em.find(MemberDTO.class, ea.getMember().getName()));
		
		for(EAPathDTO eaPath : ea.getEaApprovalPathList()) {
			eaPath.setMember(em.find(MemberDTO.class, eaPath.getMember().getName()));
		}
		
		for(EACarbonDTO eaCarbon : ea.getEaCarbonList()) {
			eaCarbon.setMember(em.find(MemberDTO.class, eaCarbon.getMember().getName()));
		}
		
		em.persist(ea);
	}
	
	public void updateEa(EADTO ea) {
		EADTO originEa = em.find(EADTO.class, ea.getSerialNo());
		
		originEa.setDate(ea.getDate());
		originEa.setTitle(ea.getTitle());
		originEa.setContent(ea.getContent());
		originEa.setEaStatus(ea.getEaStatus());
		originEa.setSaveNo(ea.getSaveNo());
		
		for(EAPathDTO eaPath : originEa.getEaApprovalPathList()) {
			em.remove(eaPath);
		}
		
		for(EACarbonDTO eaCarbon : originEa.getEaCarbonList()) {
			em.remove(eaCarbon);
		}
		
		originEa.setEaApprovalPathList(null);
		originEa.setEaCarbonList(null);
		
		em.flush();
		
		for(EAPathDTO eaPath : ea.getEaApprovalPathList()) {
			eaPath.setMember(em.find(MemberDTO.class, eaPath.getMember().getName()));
		}
		
		for(EACarbonDTO eaCarbon : ea.getEaCarbonList()) {
			eaCarbon.setMember(em.find(MemberDTO.class, eaCarbon.getMember().getName()));
		}
		
		originEa.setEaApprovalPathList(ea.getEaApprovalPathList());
		originEa.setEaCarbonList(ea.getEaCarbonList());
	}

	public void deleteAddendum(int no) {
		AddendumDTO ad = em.find(AddendumDTO.class, no);
		ad.setAddendumList(null);
		ad.setRefNo(null);
		ad.setMember(null);
		ad.setEa(null);
		em.remove(ad);
	}

	public AddendumDTO replyAddendum(AddendumDTO replyAd) {
		AddendumDTO refAd = em.find(AddendumDTO.class, replyAd.getRefNo().getNo());
		
		replyAd.setRefNo(refAd);
		replyAd.setEa(refAd.getEa());
		replyAd.setMember(em.find(MemberDTO.class, replyAd.getMember().getName()));
		replyAd.setDepth(refAd.getDepth() + 1);
		replyAd.setLength(refAd.getLength() + 1);
		
		String jpql = "SELECT a FROM AddendumDTO as a WHERE a.ea.serialNo = :no ORDER BY a.length";
		List<AddendumDTO> adList = em.createQuery(jpql, AddendumDTO.class).setParameter("no", refAd.getEa().getSerialNo()).getResultList();
		
		replyAd.getEa().getMember().getName();
		replyAd.getMember().getName();
		
		for(AddendumDTO ad : adList) {
			if(refAd.getLength() < ad.getLength()) {
				ad.setLength(ad.getLength() + 1);
			}
		}
		
		em.persist(replyAd);
		
		return replyAd;
	}

	public AddendumDTO addAddendum(AddendumDTO addAd) {
		addAd.setEa(em.find(EADTO.class, addAd.getEa().getSerialNo()));
		addAd.setMember(em.find(MemberDTO.class, addAd.getMember().getName()));
		
		String jpql = "SELECT a FROM AddendumDTO as a WHERE a.ea.serialNo = :no ORDER BY a.length";
		List<AddendumDTO> adList = em.createQuery(jpql, AddendumDTO.class).setParameter("no", addAd.getEa().getSerialNo()).getResultList();
		
		addAd.setLength(adList.size() + 1);
		
		addAd.getEa().getMember().getName();
		addAd.getMember().getName();
		
		em.persist(addAd);
		return addAd;
	}

	public void approval(String userName, int eaNo) {
		String jpql = "SELECT a FROM EAPathDTO as a WHERE a.ea.serialNo = :eaNo AND a.member.name = :userName";
		EAPathDTO eaPath = em.createQuery(jpql, EAPathDTO.class)
							 .setParameter("eaNo", eaNo)
							 .setParameter("userName", userName)
							 .getSingleResult();
		if(eaPath.getStatus() == 4) {
			eaPath.setStatus(3);
			eaPath.setDate(new java.sql.Date(System.currentTimeMillis()));
			
			EAPathDTO nextEaPath = em.find(EAPathDTO.class, new EAPathPk(eaPath.getNo() + 1, eaNo));
			
			if(nextEaPath != null) {
				nextEaPath.setStatus(4);
			} else {
				eaPath.getEa().setEaStatus(2);
			}
			
			if(eaPath.getNo() > 0) {
				EAPathDTO prevEaPath = em.find(EAPathDTO.class, new EAPathPk(eaPath.getNo() - 1, eaNo));
				prevEaPath.setStatus(5);
			}
		}
	}

	public void eaCancle(String userName, int eaNo, int type) {
		if(type == 0) {
			String jpql = "SELECT a FROM EAPathDTO as a WHERE a.ea.serialNo = :eaNo AND a.member.name = :userName";
			EAPathDTO eaPath = em.createQuery(jpql, EAPathDTO.class)
								 .setParameter("eaNo", eaNo)
								 .setParameter("userName", userName)
								 .getSingleResult();
		
			if(eaPath.getStatus() ==3) {
				eaPath.setStatus(4);
				eaPath.setDate(null);
				eaPath.getEa().setEaStatus(1);
				
				EAPathDTO nextEaPath = em.find(EAPathDTO.class, new EAPathPk(eaPath.getNo() + 1, eaNo));
				
				if(nextEaPath != null) {
					nextEaPath.setStatus(1);
				} else {
					eaPath.getEa().setEaStatus(1);
				}
				
				if(eaPath.getNo() > 0) {
					EAPathDTO prevEaPath = em.find(EAPathDTO.class, new EAPathPk(eaPath.getNo() - 1, eaNo));
					prevEaPath.setStatus(3);
				}
			}
		} else {
			EADTO ea = em.find(EADTO.class, eaNo);
			ea.setEaStatus(4);
		}
	}

	public void eaReturn(String userName, int eaNo) {
		String jpql = "SELECT a FROM EAPathDTO as a WHERE a.ea.serialNo = :eaNo AND a.member.name = :userName";
		
		EAPathDTO eaPath = em.createQuery(jpql, EAPathDTO.class)
							 .setParameter("eaNo", eaNo)
							 .setParameter("userName", userName)
							 .getSingleResult();
		
		eaPath.setStatus(2);
		eaPath.setDate(new java.sql.Date(System.currentTimeMillis()));
		eaPath.getEa().setEaStatus(3);
	}

	public void deleteEa(String userName, int eaNo) {
		em.remove(em.find(EADTO.class, eaNo));
	}

	public List<DocumentFormDTO> findeDocumentFormList() {
		String jpql = "SELECT a FROM DocumentFormDTO as a ORDER BY a.no";
		return em.createQuery(jpql, DocumentFormDTO.class).getResultList();
	}
	
}
