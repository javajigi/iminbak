$(document).ready(function() {
	$('#contents').markItUp(mySettings);
	
	$("#question").validate({
		rules: {
			title: "required",
			contents: "required"
		},
		messages: {
			title: "제목을 입력하세요.",
			contents: "내용을 입력하세요."
		}
	});
});