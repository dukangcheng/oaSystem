package com.web.oa.test.form1;

public class TestString {

	static public void main(String[] args) {
      /* String a="aaa";
              a="bbb";
              System.out.println(a);
      //String[] s = null;
     // System.out.println(s.length);
              char c='³¤';
      StringBuffer sb=new StringBuffer();
         sb.append("aaa");
         sb.append("bbb");
         System.out.println(sb);
         
       int m=20;int n=30;
       m=m+n;
       n=m-n;
       m=m-n;
       System.out.println(m+"..."+n);
       
       int x=12; int y=17;
       //x=x^y;
       //y=x^y;
       //x=x^y;
       //y=x&y;
       //x=x&y;
       
		System.out.println(x+"****"+y);*/
		int i=-1;
		System.out.println(1+" "+Integer.toBinaryString(i));
		i  >>>=10;
		System.out.println(2+" "+Integer.toBinaryString(i));
		long l=-1;
		System.out.println(3+" "+Long.toBinaryString(l));
		l  >>>=10;
		System.out.println(4+" "+Long.toBinaryString(l));
		short s=-1;
		System.out.println(5+" "+Integer.toBinaryString(s));
		s >>>=10;
		System.out.println(6+" "+Integer.toBinaryString(s));
		byte b=-1;
		System.out.println(7+" "+Integer.toBinaryString(b));
		b >>>=10;
		System.out.println(8+" "+Integer.toBinaryString(b));
		b=-1;
		System.out.println(9+" "+Integer.toBinaryString(b));
        System.out.println(10+" "+Integer.toBinaryString(b>>>10));
	}

}
