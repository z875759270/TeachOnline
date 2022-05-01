(function ($) {
    'use strict';

    //顶部分类菜单 - 获取分类
    var courseMenu = function () {
        $.ajax({
            url: Const.domain + "courseCategory/list",
            type: "GET",
            success: function (data) {
                let cList = data.content;
                let str = "";
                for (let i = 0; i < cList.length; i++) {
                    str = str.concat('<li><a href="/course/list/show/category/' + cList[i].categoryId + '">' + cList[i].categoryName + '</a></li>')
                }
                $("#courseCategoryMenu").html(str)
            }
        })
    }

    //获取热门标签
    var getHotTags = function () {
        $.ajax({
            url: Const.domain + "tag/hot/6",
            type: "POST",
            success: function (data) {
                let footerStr = "";
                let searchStr = "";
                for (let i = 0; i < data.length; i++) {
                    footerStr = footerStr.concat('<a class="tag-cloud-link text-capitalize" href="/course/list/tag/' + data[i].tagId + '">' + data[i].tagName + '</a>');
                    searchStr = searchStr.concat('<li class="list-inline-item text-capitalize"><a href="/course/list/tag/' + data[i].tagId + '">' + data[i].tagName + '</a></li>')
                }
                $("#hotTagsFooter").html(footerStr);
                $("#hotTagSearch").html(searchStr);
            }
        })
    }

    //获取热门课程
    var getHotCourse = function () {
        $.ajax({
            url: Const.domain + "course/hot/3",
            type: "GET",
            success: function (res) {
                let footerStr = "";
                for (let i = 0; i < 3; i++) {
                    footerStr = footerStr.concat("<li class=\"mb-30\">\n" +
                        "                                <div class=\"d-flex hover-up-2 transition-normal\">\n" +
                        "                                    <div class=\"post-thumb post-thumb-80 d-flex mr-15 border-radius-5 img-hover-scale overflow-hidden\">\n" +
                        "                                        <a class=\"color-white\" href=\"/course/intro/" + res[i].courseId + "\">\n" +
                        "                                            <img src=\"/media/course/img/" + res[i].courseImg + "\" alt=\"\">\n" +
                        "                                        </a>\n" +
                        "                                    </div>\n" +
                        "                                    <div class=\"post-content media-body\">\n" +
                        "                                        <h6 class=\"post-title mb-15 text-limit-2-row font-medium\"><a href=\"/course/intro/" + res[i].courseId + "\">\n" +
                        "                                            " + res[i].courseName + "</a></h6>\n" +
                        "                                        <div class=\"entry-meta meta-1 float-left font-x-small text-uppercase\">\n" +
                        "                                            <span class=\"post-on\">" + res[i].courseCreateTime.substring(0, 10) + "</span>\n" +
                        "                                            <span class=\"post-by has-dot\"><i class=\"elegant-icon arrow_triangle-right_alt2\"></i> " + res[i].courseViews + "</span>\n" +
                        "                                        </div>\n" +
                        "                                    </div>\n" +
                        "                                </div>\n" +
                        "                            </li>")
                }
                $("#hotCourseFooter").html(footerStr);
            }
        })
    }

    //获取热门收藏课程
    var getColectionCourse = function () {
        $.ajax({
            url: Const.domain + "course/collection/hot/3",
            type: "GET",
            success: function (res) {
                let courseIdList = []
                for (let i = 0; i < res.length; i++) {
                    courseIdList.push(res[i].course_id);
                }
                for (let i = 0; i < courseIdList.length; i++) {
                    $.ajax({
                        url: Const.domain + "course/find/" + courseIdList[i],
                        type: "GET",
                        success: function (res) {
                            let footerHtml = $("#hotCollectionFooter").html()
                            $("#hotCollectionFooter").html(footerHtml.concat("<li class=\"mb-30\">\n" +
                                "                                <div class=\"d-flex hover-up-2 transition-normal\">\n" +
                                "                                    <div class=\"post-thumb post-thumb-80 d-flex mr-15 border-radius-5 img-hover-scale overflow-hidden\">\n" +
                                "                                        <a class=\"color-white\" href=\"/course/intro/" + res.courseId + "\">\n" +
                                "                                            <img src=\"/media/course/img/" + res.courseImg + "\" alt=\"\">\n" +
                                "                                        </a>\n" +
                                "                                    </div>\n" +
                                "                                    <div class=\"post-content media-body\">\n" +
                                "                                        <h6 class=\"post-title mb-15 text-limit-2-row font-medium\"><a href=\"/course/intro/" + res.courseId + "\">\n" +
                                "                                            " + res.courseName + "</a></h6>\n" +
                                "                                        <div class=\"entry-meta meta-1 float-left font-x-small text-uppercase\">\n" +
                                "                                            <span class=\"post-on\">" + res.courseCreateTime.substring(0, 10) + "</span>\n" +
                                "                                            <span class=\"post-by has-dot\"><i class=\"elegant-icon arrow_triangle-right_alt2\"></i> " + res.courseViews + "</span>\n" +
                                "                                        </div>\n" +
                                "                                    </div>\n" +
                                "                                </div>\n" +
                                "                            </li>"))
                        }
                    })
                }
            }
        })
    }

    //获取热门学习课程
    var getLearningCourse = function () {
        $.ajax({
            url: Const.domain+"courseUser/hot/3",
            type: "GET",
            success: function (res) {
                let courseIdList = []
                for (let i = 0; i < res.length; i++) {
                    courseIdList.push(res[i].course_id);
                }
                for (let i = 0; i < courseIdList.length; i++) {
                    $.ajax({
                        url: Const.domain + "course/find/" + courseIdList[i],
                        type: "GET",
                        success: function (res) {
                            let footerHtml = $("#hotLearningFooter").html()
                            $("#hotLearningFooter").html(footerHtml.concat("<li class=\"mb-30\">\n" +
                                "                                <div class=\"d-flex hover-up-2 transition-normal\">\n" +
                                "                                    <div class=\"post-thumb post-thumb-80 d-flex mr-15 border-radius-5 img-hover-scale overflow-hidden\">\n" +
                                "                                        <a class=\"color-white\" href=\"/course/intro/" + res.courseId + "\">\n" +
                                "                                            <img src=\"/media/course/img/" + res.courseImg + "\" alt=\"\">\n" +
                                "                                        </a>\n" +
                                "                                    </div>\n" +
                                "                                    <div class=\"post-content media-body\">\n" +
                                "                                        <h6 class=\"post-title mb-15 text-limit-2-row font-medium\"><a href=\"/course/intro/" + res.courseId + "\">\n" +
                                "                                            " + res.courseName + "</a></h6>\n" +
                                "                                        <div class=\"entry-meta meta-1 float-left font-x-small text-uppercase\">\n" +
                                "                                            <span class=\"post-on\">" + res.courseCreateTime.substring(0, 10) + "</span>\n" +
                                "                                            <span class=\"post-by has-dot\"><i class=\"elegant-icon arrow_triangle-right_alt2\"></i> " + res.courseViews + "</span>\n" +
                                "                                        </div>\n" +
                                "                                    </div>\n" +
                                "                                </div>\n" +
                                "                            </li>"))
                        }
                    })
                }
            }
        })
    }

    // Off canvas sidebar
    var OffCanvas = function () {
        $('#off-canvas-toggle').on('click', function () {
            $('body').toggleClass("canvas-opened");
        });

        $('.dark-mark').on('click', function () {
            $('body').removeClass("canvas-opened");
        });
        $('.off-canvas-close').on('click', function () {
            $('body').removeClass("canvas-opened");
        });
    };

    // Search form
    var openSearchForm = function () {
        $('button.search-icon').on('click', function () {
            $('body').toggleClass("open-search-form");
            $('.mega-menu-item').removeClass("open");
            $("html, body").animate({scrollTop: 0}, "slow");
        });
        $('.search-close').on('click', function () {
            $('body').removeClass("open-search-form");
        });
    };

    // Mobile menu
    var mobileMenu = function () {
        var menu = $('ul#mobile-menu');
        if (menu.length) {
            menu.slicknav({
                prependTo: ".mobile_menu",
                closedSymbol: '+',
                openedSymbol: '-'
            });
        }
        ;
    };

    var SubMenu = function () {
        // $(".sub-menu").hide();
        $(".menu li.menu-item-has-children").on({
            mouseenter: function () {
                $('.sub-menu:first, .children:first', this).stop(true, true).slideDown('fast');
            },
            mouseleave: function () {
                $('.sub-menu:first, .children:first', this).stop(true, true).slideUp('fast');
            }
        });
    };

    var WidgetSubMenu = function () {
        //$(".sub-menu").hide();
        $('.menu li.menu-item-has-children').on('click', function () {
            var element = $(this);
            if (element.hasClass('open')) {
                element.removeClass('open');
                element.find('li').removeClass('open');
                element.find('ul').slideUp(200);
            } else {
                element.addClass('open');
                element.children('ul').slideDown(200);
                element.siblings('li').children('ul').slideUp(200);
                element.siblings('li').removeClass('open');
                element.siblings('li').find('li').removeClass('open');
                element.siblings('li').find('ul').slideUp(200);
            }
        });
    };

    // Slick slider
    var customSlickSlider = function () {

        // Slideshow Fade
        $('.slide-fade').slick({
            infinite: true,
            dots: false,
            arrows: true,
            autoplay: false,
            autoplaySpeed: 3000,
            fade: true,
            fadeSpeed: 1500,
            prevArrow: '<button type="button" class="slick-prev"><i class="elegant-icon arrow_left"></i></button>',
            nextArrow: '<button type="button" class="slick-next"><i class="elegant-icon arrow_right"></i></button>',
            appendArrows: '.arrow-cover',
        });

        // carausel 3 columns
        $('.carausel-3-columns').slick({
            dots: false,
            infinite: true,
            speed: 1000,
            arrows: false,
            autoplay: true,
            slidesToShow: 3,
            slidesToScroll: 1,
            loop: true,
            adaptiveHeight: true,
            responsive: [{
                breakpoint: 1024,
                settings: {
                    slidesToShow: 3,
                    slidesToScroll: 3,
                }
            },
                {
                    breakpoint: 480,
                    settings: {
                        slidesToShow: 1,
                        slidesToScroll: 1
                    }
                }
            ]
        });

        // featured slider 2
        $('.featured-slider-2-items').slick({
            slidesToShow: 1,
            slidesToScroll: 1,
            arrows: false,
            dots: false,
            fade: true,
            asNavFor: '.featured-slider-2-nav',
        });
        $('.featured-slider-2-nav').slick({
            slidesToShow: 3,
            slidesToScroll: 1,
            vertical: true,
            asNavFor: '.featured-slider-2-items',
            dots: false,
            arrows: false,
            focusOnSelect: true,
            verticalSwiping: true
        });
        // featured slider 3
        $('.featured-slider-3-items').slick({
            slidesToShow: 1,
            slidesToScroll: 1,
            arrows: true,
            dots: false,
            fade: true,
            prevArrow: '<button type="button" class="slick-prev"><i class="elegant-icon arrow_left"></i></button>',
            nextArrow: '<button type="button" class="slick-next"><i class="elegant-icon arrow_right"></i></button>',
            appendArrows: '.slider-3-arrow-cover',
        });
    };

    var typeWriter = function () {
        var TxtType = function (el, toRotate, period) {
            this.toRotate = toRotate;
            this.el = el;
            this.loopNum = 0;
            this.period = parseInt(period, 10) || 2000;
            this.txt = '';
            this.tick();
            this.isDeleting = !1
        };
        TxtType.prototype.tick = function () {
            var i = this.loopNum % this.toRotate.length;
            var fullTxt = this.toRotate[i];
            if (this.isDeleting) {
                this.txt = fullTxt.substring(0, this.txt.length - 1)
            } else {
                this.txt = fullTxt.substring(0, this.txt.length + 1)
            }
            this.el.innerHTML = '<span class="wrap">' + this.txt + '</span>';
            var that = this;
            var delta = 200 - Math.random() * 100;
            if (this.isDeleting) {
                delta /= 2
            }
            if (!this.isDeleting && this.txt === fullTxt) {
                delta = this.period;
                this.isDeleting = !0
            } else if (this.isDeleting && this.txt === '') {
                this.isDeleting = !1;
                this.loopNum++;
                delta = 500
            }
            setTimeout(function () {
                that.tick()
            }, delta)
        };
        window.onload = function () {
            var elements = document.getElementsByClassName('typewrite');
            for (var i = 0; i < elements.length; i++) {
                var toRotate = elements[i].getAttribute('data-type');
                var period = elements[i].getAttribute('data-period');
                if (toRotate) {
                    new TxtType(elements[i], JSON.parse(toRotate), period)
                }
            }
            var css = document.createElement("style");
            css.type = "text/css";
            css.innerHTML = ".typewrite > .wrap { border-right: 0.05em solid #5869DA}";
            document.body.appendChild(css)
        }
    }

    // Nice Select
    var niceSelectBox = function () {
        var nice_Select = $('select');
        if (nice_Select.length) {
            nice_Select.niceSelect("wide");
        }
    };

    //Header sticky
    var headerSticky = function () {
        $(window).on('scroll', function () {
            var scroll = $(window).scrollTop();
            if (scroll < 245) {
                $(".header-sticky").removeClass("sticky-bar");
            } else {
                $(".header-sticky").addClass("sticky-bar");
            }
        });
    };

    // Scroll up to top
    var scrollToTop = function () {
        $.scrollUp({
            scrollName: 'scrollUp', // Element ID
            topDistance: '300', // Distance from top before showing element (px)
            topSpeed: 300, // Speed back to top (ms)
            animation: 'fade', // Fade, slide, none
            animationInSpeed: 200, // Animation in speed (ms)
            animationOutSpeed: 200, // Animation out speed (ms)
            scrollText: '<i class="elegant-icon arrow_up"></i>', // Text for element
            activeOverlay: false, // Set CSS color to display scrollUp active point, e.g '#00FFFF'
        });
    };

    //VSticker
    var VSticker = function () {
        $('#news-flash').vTicker({
            speed: 800,
            pause: 3000,
            animation: 'fade',
            mousePause: false,
            showItems: 1
        });
        $('#date-time').vTicker({
            speed: 800,
            pause: 3000,
            animation: 'fade',
            mousePause: false,
            showItems: 1
        });
    };

    //sidebar sticky
    var stickySidebar = function () {
        $('.sticky-sidebar').theiaStickySidebar();
    };

    //Custom scrollbar
    var customScrollbar = function () {
        var $ = document.querySelector.bind(document);
        var ps = new PerfectScrollbar('.custom-scrollbar');
    };

    //Mega menu
    var megaMenu = function () {
        $('.sub-mega-menu .nav-pills > a').on('mouseover', function (event) {
            $(this).tab('show');
        });
    };

    //magnific Popup
    var magPopup = function () {
        if ($('.play-video').length) {
            $('.play-video').magnificPopup({
                disableOn: 700,
                type: 'iframe',
                mainClass: 'mfp-fade',
                removalDelay: 160,
                preloader: false,
                fixedContentPos: false
            });
        }
    };

    var masonryGrid = function () {
        if ($(".grid").length) {
            // init Masonry
            var $grid = $('.grid').masonry({
                itemSelector: '.grid-item',
                percentPosition: true,
                columnWidth: '.grid-sizer',
                gutter: 0
            });

            // layout Masonry after each image loads
            $grid.imagesLoaded().progress(function () {
                $grid.masonry();
            });
        }
    };

    /* More articles*/
    var moreArticles = function () {
        $.fn.vwScroller = function (options) {
            var default_options = {
                delay: 500,
                /* Milliseconds */
                position: 0.7,
                /* Multiplier for document height */
                visibleClass: '',
                invisibleClass: '',
            }

            var isVisible = false;
            var $document = $(document);
            var $window = $(window);

            options = $.extend(default_options, options);

            var observer = $.proxy(function () {
                var isInViewPort = $document.scrollTop() > (($document.height() - $window.height()) * options.position);

                if (!isVisible && isInViewPort) {
                    onVisible();
                } else if (isVisible && !isInViewPort) {
                    onInvisible();
                }
            }, this);

            var onVisible = $.proxy(function () {
                isVisible = true;

                /* Add visible class */
                if (options.visibleClass) {
                    this.addClass(options.visibleClass);
                }

                /* Remove invisible class */
                if (options.invisibleClass) {
                    this.removeClass(options.invisibleClass);
                }

            }, this);

            var onInvisible = $.proxy(function () {
                isVisible = false;

                /* Remove visible class */
                if (options.visibleClass) {
                    this.removeClass(options.visibleClass);
                }

                /* Add invisible class */
                if (options.invisibleClass) {
                    this.addClass(options.invisibleClass);
                }
            }, this);

            /* Start observe*/
            setInterval(observer, options.delay);

            return this;
        }

        // if ($.fn.vwScroller) {
        //     var $more_articles = $('.single-more-articles');
        //     $more_articles.vwScroller({ visibleClass: 'single-more-articles--visible', position: 0.55 })
        //     $more_articles.find('.single-more-articles-close-button').on('click', function() {
        //         $more_articles.hide();
        //     });
        // }

        $('button.single-more-articles-close').on('click', function () {
            $('.single-more-articles').removeClass('single-more-articles--visible');
        });
    }

    /* WOW active */
    new WOW().init();

    //Load functions
    $(document).ready(function () {
        openSearchForm();
        OffCanvas();
        customScrollbar();
        magPopup();
        scrollToTop();
        headerSticky();
        stickySidebar();
        customSlickSlider();
        megaMenu();
        mobileMenu();
        typeWriter();
        WidgetSubMenu();
        masonryGrid();
        niceSelectBox();
        moreArticles();
        VSticker();
        courseMenu();
        getHotTags()
        getHotCourse();
        getColectionCourse();
        getLearningCourse();
    });

})(jQuery);