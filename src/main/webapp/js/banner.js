var count = 0;
var $li;
$(function () { //call after the page is loaded

	$li = $("#banner-div>ul>li");
	$(".arrow-right").click(startRequest); // go to next location
	$(".arrow-left").click(function () { // go to prior location
		count--;
		if (count === -1) {
			count = $li.length - 1;
		}
		console.log(count);
		$li.eq(count).fadeIn().siblings().fadeOut();
		$(".banner-slider i").eq(count).addClass("btn_act").siblings().removeClass('btn_act');
	});
	$(".banner-slider i").mouseenter(function () { //go to  specified location
		$(this).addClass('btn_act').siblings().removeClass("btn_act");
		$li.eq($(this).index()).fadeIn().siblings().fadeOut();
		count = $(this).index();
	});

	setInterval("startRequest()", 3000); //use timer to call event
});

// go to next location
function startRequest() {
	count++;
	if (count === $li.length) {
		count = 0;
	}
	$li.eq(count).fadeIn().siblings().fadeOut();
	$(".banner-slider i").eq(count).addClass("btn_act").siblings().removeClass('btn_act');
	console.log(count);
}
