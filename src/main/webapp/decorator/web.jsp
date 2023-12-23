<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="APIurl" value="/api-cart"/>
<html>
<head>
    <title><dec:title default="Trang Chủ"/></title>
    <!-- Meta tag Keywords -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="UTF-8"/>
    <meta name="keywords"
          content="Tech_Power Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design"/>
    <link rel="shortcut icon" href="<c:url value='/images/logo.png'/>">
    <script>
        addEventListener("load", function () {
            setTimeout(hideURLbar, 0);
        }, false);

        function hideURLbar() {
            window.scrollTo(0, 1);
        }
    </script>
    <!-- //Meta tag Keywords -->

    <!-- Custom-Files -->
    <link href="<c:url value='/template/web/css/bootstrap.css'/>" rel="stylesheet" type="text/css" media="all"/>
    <!-- Bootstrap css -->
    <link href="<c:url value='/template/web/css/style.css'/>" rel="stylesheet" type="text/css" media="all"/>
    <!-- Main css -->
    <link rel="stylesheet" href="<c:url value='/template/web/css/fontawesome-all.css'/>">
    <!-- Font-Awesome-Icons-CSS -->
    <link href="<c:url value='/template/web/css/popuo-box.css'/>" rel="stylesheet" type="text/css" media="all"/>
    <!-- pop-up-box -->
    <link href="<c:url value='/template/web/css/menu.css'/>" rel="stylesheet" type="text/css" media="all"/>
    <!-- menu style -->
    <!-- flexslider -->
    <link rel="stylesheet" href="<c:url value='/template/web/css/flexslider.css'/>" type="text/css" media="screen" />
    <!-- //Custom-Files -->

    <!-- web fonts -->
    <link
            href="//fonts.googleapis.com/css?family=Lato:100,100i,300,300i,400,400i,700,700i,900,900i&amp;subset=latin-ext"
            rel="stylesheet">
    <link
            href="//fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i,800,800i&amp;subset=cyrillic,cyrillic-ext,greek,greek-ext,latin-ext,vietnamese"
            rel="stylesheet">
    <!-- //web fonts -->
    <script src="<c:url value='/template/admin/vendors/jquery/dist/jquery.min.js'/>"></script>

    <!-- Paging  -->
    <script src="<c:url value='/template/paging/jquery.twbsPagination.js' />"></script>
</head>
<body>
<%@ include file="/common/web/header.jsp" %>
<%@ include file="/common/web/menu.jsp" %>
<div class="load" style="display: none">
    <img src="<c:url value='/images/loading.gif'/>">
</div>
<dec:body/>
<%@ include file="/common/web/footer.jsp" %>

<!-- js-files -->
<!-- loading page -->
<%--<script src="<c:url value='/template/web/js/jquery-3.2.1.min.js'/>"></script>--%>
<!-- jquery -->
<%--<script src="<c:url value='/template/web/js/jquery-2.2.3.min.js'/>"></script>--%>
<!-- //jquery -->

<script>
    function formatVND(element) {
        return new Intl.NumberFormat('vi-VN', {
            style: 'currency',
            currency: 'VND'
        }).format(element);
    }

    let arrayprice = document.getElementsByClassName("item_price");
    for (let i = 0; i < arrayprice.length; i++)
        arrayprice[i].innerHTML = formatVND(arrayprice[i].innerHTML)

    let arraydiscount = document.getElementsByClassName("item_discount");
    for (let i = 0; i < arraydiscount.length; i++) {
        arraydiscount[i].innerHTML = Number.parseFloat(arraydiscount[i].innerHTML).toFixed(0);
        arraydiscount[i].innerHTML = formatVND(arraydiscount[i].innerHTML)
    }

    function loadCart(data) {
        $('#mycart tbody').html('');
        let totalPrice = 0;
        let size = 0;
        for (let i = 0; i < data.length; i++) {
            var row = [];
            row.push(
                '<tr><td class="w-15"><a href="',
                '/chi-tiet-san-pham?id=',
                data[i].productId,
                '"><img src="',
                '' + data[i].productImage,
                '" class="img-fluid img-thumbnail"></a>',
                '</td><td><a href="',
                '/chi-tiet-san-pham?id=',
                data[i].productId,
                '" class="text-secondary">',
                data[i].productName,
                '</a></td><td class="item_discount">',
                data[i].unitPrice,
                '</td><td class="qty"><form><input type="hidden" name="productId" value="',
                data[i].productId,
                '"/><input type="number" class="form-control quantity-cart" name="quantity" value="',
                data[i].quantity,
                '" min="1"></form></td><td class="item_discount">',
                data[i].unitPrice * data[i].quantity,
                '<td><form><button class="btn btn-danger btn-sm remove-item"><i class="fa fa-times"></i></button> <input type="hidden" name="productId" value="',
                data[i].productId,
                '"/></form></td></tr>'
            )
            $('#mycart tbody').append(row.join(""));
            totalPrice += data[i].unitPrice * data[i].quantity;
            size += data[i].quantity;
        }
        $('#cartModal .total-price .price').text(totalPrice)
        $('.cart-size').text(size);
        let arraydiscount = document.getElementsByClassName("item_discount");
        for (let i = 0; i < arraydiscount.length; i++) {
            if (arraydiscount[i].innerHTML.includes("₫")) continue;
            arraydiscount[i].innerHTML = Number.parseFloat(arraydiscount[i].innerHTML).toFixed(0);
            arraydiscount[i].innerHTML = formatVND(arraydiscount[i].innerHTML)
        }
    }

    $(".addToCart").click(function (e) {
        e.preventDefault();
        let data = {}
        let formElement = this.form;
        let formData = $(formElement).serializeArray();
        $.each(formData, function (i, v) {
            data['' + v.name] = v.value
        });
        data['unitPrice'] = Number.parseFloat(data['unitPrice']).toFixed(0);
        addToCart(data);
    })

    function addToCart(data) {
        $('.load').show();
        $.ajax({
            url: '${APIurl}',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
                $('.load').hide();
                if (result !== null) {
                    loadCart(result);
                    $('#cartModal').modal('show');
                }
            },
            error: function (error) {
                $('.load').hide();
            }
        })
    }

    $('#mycart').on('click', '.remove-item', function (e) {
        e.preventDefault();
        let data = {}
        let formElement = this.form;
        let formData = $(formElement).serializeArray();
        $.each(formData, function (i, v) {
            data['' + v.name] = v.value
        });
        removeFromCart(data);
    })

    function removeFromCart(data) {
        $('.load').show();
        $.ajax({
            url: '${APIurl}',
            type: 'DELETE',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
                $('.load').hide();
                if (result !== null) {
                    loadCart(result);
                    $('#cartModal').modal('show');
                }
            },
            error: function (error) {
                $('.load').hide();
            }
        })
    }

    $('#mycart').on('change', '.quantity-cart', function (e) {
        e.preventDefault();
        let data = {}
        let formElement = this.form;
        let formData = $(formElement).serializeArray();
        $.each(formData, function (i, v) {
            data['' + v.name] = v.value
        });
        updateQuantityCart(data);
    })

    function updateQuantityCart(data) {
        $('.load').show();
        $.ajax({
            url: '${APIurl}',
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
                $('.load').hide();
                if (result !== null) {
                    loadCart(result);
                    $('#cartModal').modal('show');
                }
            },
            error: function (error) {
                $('.load').hide();
            }
        })
    }
</script>

<!-- nav smooth scroll -->
<script>
    $(document).ready(function () {
        $(".dropdown").hover(
            function () {
                $('.dropdown-menu', this).stop(true, true).slideDown("fast");
                $(this).toggleClass('open');
            },
            function () {
                $('.dropdown-menu', this).stop(true, true).slideUp("fast");
                $(this).toggleClass('open');
            }
        );
    });
</script>
<!-- //nav smooth scroll -->

<!-- popup modal (for location)-->
<script src="<c:url value='/template/web/js/jquery.magnific-popup.js'/>"></script>
<script>
    $(document).ready(function () {
        $('.popup-with-zoom-anim').magnificPopup({
            type: 'inline',
            fixedContentPos: false,
            fixedBgPos: true,
            overflowY: 'auto',
            closeBtnInside: true,
            preloader: false,
            midClick: true,
            removalDelay: 300,
            mainClass: 'my-mfp-zoom-in'
        });

    });
</script>
<!-- //popup modal (for location)-->

<!-- imagezoom -->
<script src="<c:url value='/template/web/js/imagezoom.js'/>"></script>
<!-- //imagezoom -->

<script src="<c:url value='/template/web/js/jquery.flexslider.js'/>"></script>
<script>
    // Can also be used with $(document).ready()
    $(window).load(function () {
        $('.flexslider').flexslider({
            animation: "slide",
            controlNav: "thumbnails"
        });
    });
</script>
<!-- //FlexSlider-->

<!-- scroll seller -->
<script src="<c:url value='/template/web/js/scroll.js'/>"></script>
<!-- //scroll seller -->

<!-- smoothscroll -->
<script src="<c:url value='/template/web/js/SmoothScroll.min.js'/>"></script>
<!-- //smoothscroll -->

<!-- start-smooth-scrolling -->
<script src="<c:url value='/template/web/js/move-top.js'/>"></script>
<script src="<c:url value='/template/web/js/easing.js'/>"></script>
<script>
    jQuery(document).ready(function ($) {
        $(".scroll").click(function (event) {
            event.preventDefault();

            $('html,body').animate({
                scrollTop: $(this.hash).offset().top
            }, 1000);
        });
    });
</script>
<!-- //end-smooth-scrolling -->

<!-- smooth-scrolling-of-move-up -->
<script>
    $(document).ready(function () {
        /*
        var defaults = {
            containerID: 'toTop', // fading element id
            containerHoverID: 'toTopHover', // fading element hover id
            scrollSpeed: 1200,
            easingType: 'linear'
        };
        */
        $().UItoTop({
            easingType: 'easeOutQuart'
        });

    });
</script>
<!-- //smooth-scrolling-of-move-up -->

<!-- for bootstrap working -->
<script src="<c:url value='/template/web/js/bootstrap.js'/>"></script>
<!-- //for bootstrap working -->
<!-- //js-files -->
</body>
</html>