<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="/header" />
<script type="text/javascript"
	src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
<link rel="stylesheet" href="support.css">

<script>
	function generateMerchantUid() {
		return 'order_no_' + new Date().getTime();
	}

	function KGpay() {
		$.ajax({
			type:'GET',
			url:'getContributor',
			success:function(user){
				const Amount = document
				.querySelector('input[name="donation-amount"]:checked').value;
				const merchantUid = generateMerchantUid();

				IMP.init("imp82268541"); // 아임포트 가맹점 식별코드
				IMP.request_pay({
					pg : "kcp",
					pay_method : "card",
					merchant_uid : merchantUid,
					name : "멍냥고홈",
					amount : parseInt(Amount),
					buyer_email : user.email,
					buyer_name : user.name,
					buyer_tel : user.tel,
					buyer_addr : user.Address
				}, function(rsp) { // callback
					console.log(rsp); // 응답 로그 확인
					if (rsp.success) {
						$.ajax({
							type : 'POST',
							url : '/verify/' + rsp.imp_uid
						}).done(function(data) {
							if (rsp.paid_amount === data.response.amount) {
								alert("결제 성공");
							} else {
								alert("결제 실패");
							}
						});
					} else {
						alert("결제 실패: " + rsp.error_msg);
					}
				});
			},
			error: function(error) {
				alert("구매자 정보를 가져오는 데 실패했습니다.");
			}
		});
	}
</script>

<div class="banner">
	<div>
		<h1>Donate</h1>
		<p>후원 하기</p>
	</div>
</div>

<div class="sub-contents" id="contents">

	<div class="donation-container">

		<div class="left_box">
			<img src="img/dog_donate.png" alt="dog_image">
		</div>
		<div class="donation-info">
			<p class="donation-text">
				사랑이 필요한 아이들에게<br> 따뜻한 보호자가 되어주세요.<br> <span>함께해요,
					후원으로...</span>
			</p>
			<div class="donation-amount">
				<ul class="amount-options">
					<li><input type="radio" id="amount-10000"
						name="donation-amount" value="1000" class="radio-button" checked>
						<label for="amount-10000" class="amount-option">10,000원</label></li>
					<li><input type="radio" id="amount-15000"
						name="donation-amount" value="15000" class="radio-button">
						<label for="amount-15000" class="amount-option">15,000원</label></li>
					<li><input type="radio" id="amount-20000"
						name="donation-amount" value="20000" class="radio-button">
						<label for="amount-20000" class="amount-option">20,000원</label></li>
					<li><input type="radio" id="amount-30000"
						name="donation-amount" value="30000" class="radio-button">
						<label for="amount-30000" class="amount-option">30,000원</label></li>
					<li><input type="radio" id="amount-50000"
						name="donation-amount" value="50000" class="radio-button">
						<label for="amount-50000" class="amount-option">50,000원</label></li>
					<li><input type="radio" id="amount-100000"
						name="donation-amount" value="100000" class="radio-button">
						<label for="amount-100000" class="amount-option">100,000원</label></li>
				</ul>
			</div>
			<div class="supportBox">
				<button onclick="KGpay()" id="supportBtn">후원하기</button>
			</div>
		</div>
		<div class="right_box">
			<img src="img/cat_donate.png" alt="dog_image">
		</div>
	</div>
</div>


<c:import url="/footer" />