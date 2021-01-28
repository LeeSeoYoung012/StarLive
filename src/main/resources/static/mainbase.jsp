<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <!-- include css/js -->
    <link href="resources/main/starliveBase.css" rel="stylesheet" type="text/css">

</head>

<body>
<div id="header">
    <div class="gnb_top">
        <h1>
            <a class="btn_v" href="/home">
                <span class="logo_v">스타와 팬이 함께하는 우리만의 커뮤니티 V LIVE</span>
            </a>
        </h1>

        <div class="navigation">
            <ul>
                <li><a href="/home" class="on"><span class="menu1">홈</span></a></li>
                <li><a href="/channels" class=""><span class="menu3">채널</span></a></li>
            </ul>
        </div>

        <div class="gnb_info">

            <div class="login_area">
                <a href="#" class="btn_login" style="" onclick="vlive.ba.clickEvent(this);vlive.tv.auth.handler.login(event);return false;" data-ba-sceneid="web_g_menu" data-ba-classifier="login_button">로그인</a>
                <div class="btn_more_wrap _top_nick_area">
                    <button type="button" class="btn_more _userInfoBtn" style="display:none" onclick="vlive.ba.clickEvent(this);" data-ba-sceneid="web_g_menu" data-ba-classifier="web_g_more">
                        <span class="tooltip"><span class="tooltip_text">더보기</span></span>
                    </button>
                    <div class="lyr" style="display:none;">
                        <div class="login_btn">
                            <div class="inner"><a href="/my/profile" class="txt -profile" onclick="vlive.ba.clickEvent(this);" data-ba-sceneid="web_g_menu" data-ba-classifier="web_g_profile">프로필 설정</a></div>
                            <div class="inner"><a href="/my" class="txt -my" onclick="vlive.ba.clickEvent(this);" data-ba-sceneid="web_g_menu" data-ba-classifier="web_g_mypage">마이페이지</a></div>
                            <div class="inner"><a href="/auth/logout" class="btn_logout" onclick="vlive.ba.clickEvent(this);vlive.tv.auth.handler.logout();" data-ba-sceneid="web_g_menu" data-ba-classifier="logout_button"><span class="inner">로그아웃</span></a></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>

<div id="footer" style="">

</div>