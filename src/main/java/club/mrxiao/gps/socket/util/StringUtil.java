package club.mrxiao.gps.socket.util;

/**
 * 字符串工具
 * @author xiaoyu
 */
public class StringUtil {
    /**
     * 判断字符串是否为空
     *
     * @param s 要判断的字符串
     * @return 是否为空
     */
    public static boolean isNullStr(String s) {
        return s == null || s.trim().length() <= 0;
    }
}
