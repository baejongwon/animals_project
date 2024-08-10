<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="/header" />

<style>

</style>

<div class="banner">
	<div>
		<p>입양대기동물 현황</p>
	</div>
</div>

<div class="content_box_1" style="width: 1200px; margin: 0 auto; margin-top: 35px;">
    <table cellspacing="0" cellpadding="0" border="0" width="100%" style="table-layout: fixed;">
        <tbody>
            <tr>
                <c:forEach var="board" items="${boards}" varStatus="status">
                    <td valign="top" align="center" style="width: 250px; padding: 5px;">
                        <table valign="top" cellspacing="0" style="width: 100%;">
                            <tbody>
                                <tr>
                                    <td style="padding:3px; border:1px solid #efefef;" bgcolor="#FFFFFF">
                                        <a href="animal_find_Content?no=${board.animal_no}">
                                            <img src="" align="absmiddle" border="0" width="250" height="200" style="border-radius: 10px 10px;">
                                        </a>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="left" valign="top" class="font_st_05" style="padding-left:3px;">
                                        <table cellspacing="0" style="width: 100%;">
                                            <tbody>
                                                <tr>
                                                    <td style="padding-top:5px; width:100%; line-height:18px;" align="left">
                                                        <span style="color:#f39a68; font:11px '돋움';">${board.animal_no}</span>
                                                        <a href="animal_find_Content?no=${board.animal_no}">${board.nm}</a>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td style="padding-top:3px; width:100%;" align="left">
                                                        <span style="font:11px '돋움'; color:#999999;">${board.entrnc_date}</span>
                                                    </td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </td>
                </c:forEach>
            </tr>
        </tbody>
    </table>
</div>

<c:import url="/footer" />
