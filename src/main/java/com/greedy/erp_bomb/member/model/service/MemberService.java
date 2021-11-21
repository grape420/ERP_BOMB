package com.greedy.erp_bomb.member.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.greedy.erp_bomb.member.model.dao.MemberDAO;
import com.greedy.erp_bomb.member.model.dto.AuthorityDTO;
import com.greedy.erp_bomb.member.model.dto.MemberDTO;
import com.greedy.erp_bomb.member.model.dto.MemberRoleDTO;
import com.greedy.erp_bomb.member.model.dto.UserImpl;

@Service
public class MemberService implements UserDetailsService {

	private MemberDAO memberDAO;

	@Autowired
	public MemberService(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}

	/* 사용자 정보를 조회해서 UserDetails 타입을 반환하는 메소드 */
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

		/* 우리가 만든 MemberDTO를 쓸 수 없고 User객체를 반환해야 한다. */
		MemberDTO member = memberDAO.findMemberById(name);

		if (member == null) {
			member = new MemberDTO();
		}

		List<GrantedAuthority> authorities = new ArrayList<>();
		if (member.getMemberRoleList() != null) {
			List<MemberRoleDTO> roleList = member.getMemberRoleList();

			for (int i = 0; i < roleList.size(); i++) {
				AuthorityDTO authority = roleList.get(i).getAuthority();

				authorities.add(new SimpleGrantedAuthority(authority.getName()));
			}
		}

		UserImpl user = new UserImpl(member.getName(), member.getPwd(), authorities);
		user.setDetails(member);

		return user;
	}
}
