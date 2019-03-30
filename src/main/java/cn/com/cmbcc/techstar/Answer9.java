package cn.com.cmbcc.techstar;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Answer9 {

    static public HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();

    static {
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
    }

    private static Map<Character, Integer> dictIndex = new HashMap<>();
    private static Map<Integer, Set<Character>> dict = new HashMap<>();
    private static Map<String, Set<Character>> spellDict = new HashMap<>();


    public static void startRun(String chaneseArray){
        char[] arr = chaneseArray.toCharArray();

        for (Character character : arr) {
            if (getSpell(character) == null)
                return;
        }


        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<String> spellList = new ArrayList<>();
        for (Character character : arr) {
            String spell = getSpell(character);
            Set<Character> characters = spellDict.get(spell);
            if (characters == null || characters.size() == 0) {
                spellList = new ArrayList<>();
                break;
            }
            if (spellList.size() == 0) {
                List<String> finalList = spellList;
                //characters.forEach(p -> finalList.add(p.toString()));
                Iterator<Character> it = characters.iterator();
                while (it.hasNext()) {
                    Character str = it.next();
                    finalList.add(str.toString());
                   // System.out.println(str);
                }
                continue;
            }
            List<String> mid = new ArrayList<>();
            //spellList.forEach(p -> characters.forEach(charMid -> mid.add(p + charMid.toString())));
           for (int k = 0 ;k < spellList.size();k++){
               String p = spellList.get(k);
               Iterator<Character> it = characters.iterator();
               while (it.hasNext()) {
                   Character charMid = it.next();
                   mid.add(p+charMid.toString());
                   //finalList.add(str.toString());
                   // System.out.println(str);
               }
           }
            spellList = mid;
        }


        List<String> list = new ArrayList<>();
        for (Character character : arr) {
            Integer index = dictIndex.get(character);
            Set<Character> characters = dict.get(index);
            if (characters == null || characters.size() == 0) {
                list = new ArrayList<>();
                break;
            }
            if (list.size() == 0) {
                List<String> finalList = list;
                //characters.forEach(p -> finalList.add(p.toString()));
                Iterator<Character> it = characters.iterator();
                while (it.hasNext()) {
                    Character str = it.next();
                    finalList.add(str.toString());
                    // System.out.println(str);
                }
                continue;
            }
            List<String> mid = new ArrayList<>();
            //list.forEach(p -> characters.forEach(charMid -> mid.add(p + charMid.toString())));
            for (int m = list.size();m<list.size();m++){
                String str = list.get(m);
                Iterator<Character> it = characters.iterator();
                while (it.hasNext()) {
                    Character charMid = it.next();
                    mid.add(str + charMid.toString());
                    // System.out.println(str);
                }
            }
            list = mid;
        }

        list.addAll(spellList);
        int len = list.size();
        for (int n = 0;n < len;n++){
            if (n == len-1) {
                System.out.printf(list.get(n));
            }else{
                System.out.printf(list.get(n) + ", ");
            }

        }
        //System.out.println(String.join(",", list));
    }


    private static List<String> readFile() throws IOException {
        InputStream inputStream = Answer9.class.getClassLoader().getResourceAsStream("simu-chinese.txt");

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        List<String> ret = new ArrayList<>();
        String mid;
        while ((mid = bufferedReader.readLine()) != null) {
            if ("".equals(mid)) {
                continue;
            }
            ret.add(mid);
        }
        return ret;
    }

    public static void init() throws IOException {

        List<String> fileList = readFile();
        for (String line : fileList) {
            String[] split = line.split(",");
            int num = Integer.valueOf(split[0]);
            char mid = split[1].toCharArray()[0];
            //按照相同序号归类
            if (dict.containsKey(num)) {
                dict.get(num).add(mid);
            } else {
                dict.put(num, new HashSet<>(Collections.singleton(mid)));

            }

            //按照拼音归类
            dictIndex.put(mid, num);
            String spell = getSpell(mid);
            if (spell == null) {
                continue;
            }
            if (spellDict.containsKey(spell)) {
                spellDict.get(spell).add(mid);
            } else {
                spellDict.put(spell, new HashSet<>(Collections.singleton(mid)));
            }
        }

    }

    public static String getSpell(char aloneArr) {
        if (aloneArr > 128) {
            try {
                String mid = PinyinHelper.toHanyuPinyinStringArray(aloneArr, defaultFormat)[0];
                if (mid != null) {
                    return mid;
                }
            } catch (Exception e) {
            }
        }
        return null;

    }

}
