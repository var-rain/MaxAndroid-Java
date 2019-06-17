package net.lingin.max.android.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by: var_rain.
 * Created date: 2018/10/21.
 * Description: 字符串工具类
 */
public class TextUtils {

    /**
     * 判断字符串是否为空
     *
     * @param text 字符串
     * @return 为空则返回true, 不为空则返回false
     */
    public static boolean empty(String text) {
        return text == null || text.replace(" ", "").length() <= 0;
    }

    /**
     * 去除字符串中的空格,回车,换行符,制表符等
     *
     * @param str 源字符串
     * @return 格式化后的字符串
     */
    public static String clean(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

    /**
     * 验证手机号是否合法
     * <p>
     * 中国电信号段: 133、149、153、173、177、180、181、189、199
     * <p>
     * 中国联通号段: 130、131、132、145、155、156、166、175、176、185、186
     * <p>
     * 中国移动号段: 134(0-8)、135、136、137、138、139、147、150、151、152、157、158、159、178、182、183、184、187、188、198
     * <p>
     * 其他号段:
     * <p>
     * 14号段以前为上网卡专属号段，如中国联通的是145，中国移动的是147等等。
     * <p>
     * 虚拟运营商:
     * 电信: 1700、1701、1702
     * 移动: 1703、1705、1706
     * 联通: 1704、1707、1708、1709、171
     * 卫星通信: 1349
     *
     * @param str 手机号码
     * @return 如果是正常的手机号则返回true, 不是则返回false
     */
    public static boolean phone(String str) {
        if (str.length() != 11) {
            return false;
        } else {
            String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(str);
            return m.matches();
        }
    }
}
