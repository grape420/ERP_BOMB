/* 정규표현식을 이용 */
$("#name").change(function() {
	var nameExp = /^[가-힣0-9a-zA-Z]{2,}$/;

	if (!nameExp.test($(this).val())) {
		$("#name-result").text("이름을 다시 입력하세요").css("color", "red");
		$(this).val("").focus();
		nameFlag = false;
	} else {
		$("#name-result").text("").css("color", "green");
	}
})

$("#password").change(function() {
	var passwordEmp = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&]{8,16}/;

	if (!passwordEmp.test($(this).val())) {
		$("#password-result").text("영문, 숫자, 특수문자를 반드시 포함한 8~16글자로 작성 해 주세요(영문 대소문자 구분)").css("color", "red");
		$(this).focus();
	} else {
		$("#password-result").text("").css("color", "green");
	}
})

$("#password-check").change(function() {
	if ($("#password").val() != $(this).val()) {
		$("#password-check-result").text("비밀번호가 일치하지 않습니다.").css("color", "red");
		$(this).focus();
		passwordFlag = false;
	} else {
		$("#password-check-result").text("").css("color", "green");
		passwordFlag = true;
	}
})

$("#birth").change(function() {
	var birthExp = /^(19|20)[0-9]{2}(0[1-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])$/;

	if (!birthExp.test($(this).val())) {
		$("#birth-result").text("올바르지 않은 생년월일입니다.").css("color", "red");
		$(this).focus();
		birthFlag = false;
	} else {
		$("#birth-result").text("").css("color", "green");
		birthFlag = true;
	}
})

$("#phone").change(function() {
	/*var phoneExp = /^01(?:0|1|[6-9])[0-9]{8}$/;*/
	var phoneExp = /^01(?:0|1|[6-9])(?:\d{3}|\d{4})\d{4}$/;

	if (!phoneExp.test($(this).val())) {
		$("#phone-result").text("올바르지 않은 휴대폰 번호 형식입니다.").css("color", "red");
		$(this).focus();
		phoneFlag = false;
	} else {
		$("#phone-result").text("").css("color", "green");
		phoneFlag = true;
	}
})

$("#empNo").change(function() {
	/*var empNoExp = /^[0-9]+$/;*/
	var empNoExp = /^[0-9]+$/;

	if (!empNoExp.test($(this).val())) {
		$("#empNo-result").text("올바르지 않은 형식입니다.").css("color", "red");
		$(this).focus();
		empNoFlag = false;
	} else {
		$("#empNo-result").text("").css("color", "green");
		empNoFlag = true;
	}
})

$("#regularPay").change(function() {
	/*var regularPayExp = /^[0-9]+$/;*/
	var regularPayExp = /^[0-9]+$/;

	if (!regularPayExp.test($(this).val())) {
		$("#regularPay-result").text("올바르지 않은 형식입니다.").css("color", "red");
		$(this).focus();
		regularPayFlag = false;
	} else {
		$("#regularPay-result").text("").css("color", "green");
		regularPayFlag = true;
	}
})

$("#annualIncome").change(function() {
	/*var annualIncomeExp = /^[0-9]+$/;*/
	var annualIncomeExp = /^[0-9]+$/;

	if (!annualIncomeExp.test($(this).val())) {
		$("#annualIncome-result").text("올바르지 않은 형식입니다.").css("color", "red");
		$(this).focus();
		annualIncomeFlag = false;
	} else {
		$("#annualIncome-result").text("").css("color", "green");
		annualIncomeFlag = true;
	}
})

$("#email").change(function() {
	var emailExp = /^[a-z0-9_+.-]+@([a-z0-9-]+\.)+[a-z0-9]{2,4}$/
	
	if (!emailExp.test($(this).val())) {
		$("#email-result").text("올바르지 않은 이메일 형식입니다.").css("color", "red");
		$(this).focus();
		emailFlag = false;
	} else {
		$("#email-result").text("").css("color", "green");
		emailFlag = true;
	}
})

$("#agree").change(function() {
	if ($("#agree").prop('checked')) {
		$("#agree-result").text("").css("color", "red");
	}
});