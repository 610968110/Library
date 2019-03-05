package com.lbx.library.utils;

/**
 * .  ┏┓　　　┏┓
 * .┏┛┻━━━┛┻┓
 * .┃　　　　　　　┃
 * .┃　　　━　　　┃
 * .┃　┳┛　┗┳　┃
 * .┃　　　　　　　┃
 * .┃　　　┻　　　┃
 * .┃　　　　　　　┃
 * .┗━┓　　　┏━┛
 * .    ┃　　　┃        神兽保佑
 * .    ┃　　　┃          代码无BUG!
 * .    ┃　　　┗━━━┓
 * .    ┃　　　　　　　┣┓
 * .    ┃　　　　　　　┏┛
 * .    ┗┓┓┏━┳┓┏┛
 * .      ┃┫┫　┃┫┫
 * .      ┗┻┛　┗┻┛
 *
 * @author lbx
 * @date 2019/3/4.
 */

public class StringUtil {
    public static String bytesToHexString(byte[] paramArrayOfByte) {
        StringBuilder localStringBuilder = new StringBuilder("");
        if ((paramArrayOfByte == null) || (paramArrayOfByte.length <= 0)) {
            return null;
        }
        for (byte aParamArrayOfByte : paramArrayOfByte) {
            String str = Integer.toHexString(0xFF & aParamArrayOfByte);
            if (str.length() < 2) {
                localStringBuilder.append(0);
            }
            localStringBuilder.append(str);
        }
        return localStringBuilder.toString();
    }
}
