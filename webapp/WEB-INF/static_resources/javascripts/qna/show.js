$(document).ready(function(){
	var nickNames = new Array();
	
	$('#contents').markItUp(mySettings);
	
	$("#answer").validate({
		rules: {
			contents: "required",
			name: "required",
			rawPassword: "required"
		},
		messages: {
			contents: "내용을 입력하세요.",
			name: "이름을 입력하세요.",
			rawPassword: "비밀번호를 입력하세요."
		}
	});
	
	setShowRealSizeImg();

	function setShowRealSizeImg() {
		var images = $('div.doc div.text img');
		var imageUrl = images.attr('src');

		images.wrap('<a href="'+imageUrl+'" target="_blank"></a>');
	}
});