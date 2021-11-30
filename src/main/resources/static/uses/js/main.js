$(document).ready(function () {
  $('body').on("mousewheel", function (event, delta) {
      if(!$(event.target).hasClass("list")) {
        if (delta > 0) {
            $("#scrollTop").hide();
        } else {
            $("#scrollTop").show();
        }
      }
  });

  $(".select-size").on("click", function () {
    $(".select ul").show();
    $(this).addClass("on");
  });

  $("body").on("mouseover", function (e) {
    if(!$(e.target).hasClass("select-size") && !$(e.target).hasClass("lists") && !$(e.target).hasClass("list")) {
        $(".select ul").hide();
        $(".select-size").removeClass("on");
    }
  });
});
