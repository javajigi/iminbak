$(document).ready(function() {
	$('#contents').markItUp(mySettings);
	
	$("#board").validate({
		rules: {
			title: "required",
			contents: "required",
			name: "required",
			password: "required"
		},
		messages: {
			title: "제목을 입력하세요.",
			contents: "내용을 입력하세요.",
			name: "이름을 입력하세요.",
			password: "비밀번호를 입력하세요."
		}
	});
});