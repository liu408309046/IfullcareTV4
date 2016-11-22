package com.kunyuanzhihui.ifullcaretv.net;

/**
 * 说明：
 * 作者： 刘雅俊
 * 时间：2016/11/16- 17:10.
 */

public class TvAPI {

    static String api_address = "http://120.76.212.89/ApiSlinw/index.php?";

    //生成二维码链接
    public static String GET_TV_LOGIN_QRCODE = api_address + "p=user&a=tv_Sign&m=establish_data_tv";

    //监控是否扫码
    public static String MONITORING_QRCODE = api_address + "p=user&a=tv_Sign&m=auto_login";

    //获取我的家庭组
    public static String GET_FAMILY_LIST = api_address + "p=family&a=family&m=index_list";

    //获取我的家庭成员
    public static String GET_FAMILY_MEMBERS = api_address + "p=family&a=family&m=index";

    //获取我的家庭成员信息
    public static String GET_FAMILY_MEMBER_INFO = api_address + "p=family&a=family&m=see_information";

    //获取我的家庭添加成员
    public static String GET_FAMILY_ADD_MEMBER = api_address + "p=family&a=family&m=add_member";

    //获取我的家庭删除成员
    public static String GET_FAMILY_DELETE_MEMBER = api_address + "p=family&a=family&m=delete_role";

    //获取服务类别
    public static String GET_SERVICES_TYPE = api_address + "p=service&a=service&m=get_sub_type";

    //获取服务列表
    public static String GET_SERVICES_LIST = api_address + "p=service&a=service&m=service_children_app";

    //获取服务详情
    public static String GET_SERVICES_DETAILS = api_address + "p=service&a=service&m=service_details";

    //获取服务人员排班
    public static String GET_SERVICES_TIMES = api_address + "p=service&a=service&m=get_service_time";

    //获取服务评论
    public static String GET_SERVICES_COMMENT = api_address + "p=service&a=service&m=get_new_comment";

    //获取商品分类
    public static String GET_HEALTHSHOP_TYPE_MENU = api_address + "p=shop&a=goods&m=act_list";

    //获取商品列表
    public static String GET_HEALTHSHOP_GOODSLIST = api_address + "p=shop&a=goods&m=get_goods_list";

    //获取分类目录下的商品
    public static String GET_HEALTHSHOP_BY_TYPE = api_address + "p=shop&a=goods&m=cat_goods_list";
}
