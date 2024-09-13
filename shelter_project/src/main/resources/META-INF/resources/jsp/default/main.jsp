<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div align="center" class="main_div">
<div id="jssor_1" style="position:relative;margin:0 auto;top:0px;left:0px;width:1300px;height:500px;overflow:hidden;visibility:hidden;">
        <!-- Loading Screen -->
        <div data-u="loading" style="position: absolute; top: 0px; left: 0px;">
            <div style="filter: alpha(opacity=70); opacity: 0.7; position: absolute; display: block; top: 0px; left: 0px; width: 100%; height: 100%;"></div>
            <div style="position:absolute;display:block;background:url('img/loading.gif') no-repeat center center;top:0px;left:0px;width:100%;height:100%;"></div>
        </div>
        <div data-u="slides" style="cursor:default;position:relative;top:0px;left:0px;width:1300px;height:500px;overflow:hidden;">
            <div>
                <img data-u="image" src="img/slide_img1.jpg" />
            </div>
            <div>
                <img data-u="image" src="img/slide_img2.jpg" />
            </div>
            <div>
                <img data-u="image" src="img/slide_img3.jpg" />
            </div>
            <div>
                <img data-u="image" src="img/slide_img4.jpg" />
            </div>
        </div>
        <!-- Bullet Navigator -->
       <div data-u="navigator" class="jssorb057" style="position:absolute;bottom:16px;right:16px;" data-autocenter="1" data-scale="0.5" data-scale-bottom="0.75">
            <div data-u="prototype" class="i" style="width:14px;height:14px;">
                <svg viewbox="0 0 16000 16000" style="position:absolute;top:0;left:0;width:100%;height:100%;">
                    <circle class="b" cx="8000" cy="8000" r="5000"></circle>
                </svg>
            </div>
        </div>
        <!-- Arrow Navigator -->
        <!-- 
        <span data-u="arrowleft" class="jssora22l" style="top:0px;left:8px;width:40px;height:58px;" data-autocenter="2"></span>
        <span data-u="arrowright" class="jssora22r" style="top:0px;right:8px;width:40px;height:58px;" data-autocenter="2"></span>
         -->
    </div>            
	
	<!-- 컨텐츠 내용 -->
	
		<div>
		<h4 style="text-align: center; margin-top: 50px;color: #595959;">
		<span style="font-size: 45px; font-weight: 900">영원한 <span style="color: #F4A460;">가족이</span> 되어주세요</span></h4>
		</div>
		
		<div  class="content_box_1" style="width: 1200px; margin: 0 auto; margin-top: 35px;">
			
		<table cellspacing="0" cellpadding="0" border="0" width="100%">
			<tbody><tr>
				<td valign="top" align="center"><table valign="top" cellspacing="0" style="width:250px;">
		<tbody><tr>
			<c:forEach var="board" items="${boards}">
					<td valign="top" align="center"><table valign="top" cellspacing="0" style="width:250px;">
				<tbody><tr>
					<td style="width:250px;" valign="top">
						<table cellspacing="0">
						<tbody><tr>
							<td style="padding:3px; border:1px solid #efefef;" bgcolor="#FFFFFF" ><a href="personalContent?no=${board.animal_no}"><img src="${imagePathMap[board.animal_no] }" align="absmiddle" border="0" width="250" height="200" style="border-radius: 10px 10px;"></a></td>
						</tr>
						</tbody></table>
					</td>
				</tr>
				<tr>
					<td align="left" valign="top" class="font_st_05" style="padding-left:3px;">
						<table cellspacing="0">
						<tbody><tr>
							<td style="padding-top:5px; width:250px; line-height:18px;" align="left">
								<span style="color:#f39a68; font:11px '돋움';">${board.animal_no}</span>
								<a href="bbs_detail.php?bbs_num=130&amp;id=&amp;tb=board_bestseller"> </a><a href="personalContent?no=${board.animal_no}" style="color:#000">${board.nm}</a> 
							</td>
						</tr>
						<tr>
							<td style="padding-top:3px; width:250px;" align="left">
								<table cellspacing="0">
								<tbody><tr>
									<td style="font:11px '돋움'; color:#999999; letter-spacing:-1px;">${board.author}</td>
									<td style="font:11px '돋움'; color:#d3d3d3; padding:0 5px;">|</td>
									<td style="font:11px '돋움'; color:#999999;">${board.time}</td>
								</tr>
								</tbody></table>
							</td>
						</tr>
						</tbody></table>
					</td>
				</tr>
				</tbody></table>
				</td>
			</c:forEach>
		</tr></tbody></table>
			
		  </div>
	<a href="personalBoards"><div class="more">더 보기 + </div></a>
	
	<div class="bottomBanner" style="width: 1200px; margin: 0 auto; margin-top: 60px; ">
		<img src="../img/banner1.jpg" alt="banner_1">
	</div>
	
		<!--동물 사전-->
		<div class="tableArea clearfix">
			
		<div style="margin-bottom: 50px;">
		<h4 style="text-align: center; margin-top: 50px;color: #595959;">
		<span style="font-size: 45px; font-weight: 700;">우리집 <span style="color: #F4A460;">반려동물</span> 지식 나눔터</span></h4>
		</div>
			
		<div class="animal_info">
		<ul class="">
			<li>
				<div class="photo"><a href="/infoBoard"><img class="img_left" src="../img/팁1.jpg" title=""></a></div>
			</li>
			<li>
				<div class="photo"><a href="/infoBoard"><img class="img_left" src="../img/팁2.jpg" title=""></a></div>
			</li>
			<li>
				<div class="photo"><a href="/infoBoard"><img class="img_left" src="../img/팁3.jpg" title=""></a></div>
			</li>
			<li>
				<div class="photo"><a href="/infoBoard"><img class="img_left" src="../img/팁4.jpg" title=""></a></div>
			</li>
		</ul>
		</div>
	</div>
		
</div>

