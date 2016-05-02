package com.makesrc;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/** Created by Brent Burbidge on 4/23/2016. **/
public class VCard extends Person {

    //region Properties
    private String avatar;
    private int version;

    private String getAvatar() {
        String result = "";
        File f = new File(avatar);
        try {
            result = TextUtil.encodeFile(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    //region Property Getters And Setters
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    private int getVersion(File og) {
        int version = 1;

        try (BufferedReader br = new BufferedReader(new FileReader(og))) {
            String line;
            while ((line = br.readLine()) != null) if (line.contains("VERSION:")) {
                version = Integer.parseInt(line.replace("VERSION:", ""));
                version = version + 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return version;
    }

    private void setVersion(File og) {
        this.version = getVersion(og);
    }

    //endregion

    private void setVersion(int v) {
        this.version = v;
    }

    private String getRevision() {
        SimpleDateFormat sdfr = new SimpleDateFormat("yyyyMMdd'T'HHmmssZ");
        String rev = "";
        try {
            rev = sdfr.format(Calendar.getInstance().getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rev;
    }
    //endregion

    /** @return VCard string */
    private String VCard() {
        String str = "";
        if (!getAvatar().isEmpty()) {
            str = "BEGIN:VCARD\n" +
                    "VERSION:" + version + "\n" +
                    "N:" + getLastName() + ", " + getFirstName() + "\n" +
                    "FN:" + getFirstName() + " " + getLastName() + "\n" +
                    "TEL;CELL;VOICE:" + getPhone() + "\n" +
                    "X-MS-OL-DEFAULT-POSTAL-ADDRESS:0\n" +
                    "EMAIL;PREF;INTERNET:" + getEmail() + "\n" +
                    "PHOTO;TYPE=JPEG;ENCODING=BASE64:\n" + getAvatar() + "\n" +
                    "REV:" + getRevision() + "\n" +
                    "END:VCARD";
        } else {
            str = "BEGIN:VCARD\n" +
                    "VERSION:" + version + "\n" +
                    "N:" + getLastName() + ", " + getFirstName() + "\n" +
                    "FN:" + getFirstName() + " " + getLastName() + "\n" +
                    "TEL;CELL;VOICE;VALUE=uri:" + getPhone() + "\n" +
                    "EMAIL;PREF;INTERNET:" + getEmail() + "\n" +
                    "REV:" + getRevision() + "\n" +
                    "END:VCARD";
        }
        return str;
    }

    public void VCardGenerate() {
        File vcf = new File(getFirstName() + getLastName() + ".vcf");
        if (vcf.exists()) {
            setVersion(vcf);
        } else {
            setVersion(1);
        }

        try (BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(vcf.getName()), "UTF-8"))) {
            out.write(VCard());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
