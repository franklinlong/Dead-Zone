/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import static sprite.Sprite.mapRGB;

/**
 *
 * @author USER
 */
public class Zona {
    private int index;
    float posX, posY;
    
    public Zona(float posX, float posY){
        this.posX = posX;
        this.posY = posY;
        aggiorna(posX,posY);
    }
 
    public boolean equals(Zona z){
        return this.index == z.index;
    }
    
    public boolean aggiorna(float posX, float posY){
        //se è andato contro un muro
        int old = index;
        int pixel = mapRGB.getRGB((int)posX,(int)posY);
        int blue = pixel & 0xff;
        switch(blue){
            case 45:
                index = 1;
                break;
            case 50:
                index = 2;
                break;
            case 55:
                index = 3;
                break;
            case 60:
                index = 4;
                break;
            case 65:
                index = 5;
                break;
            case 70:
                index = 6;
                break;
            case 75:
                index = 7;
                break;
            case 80:
                index = 8;
                break;
            case 85:
                index = 9;
                break;
            case 90:
                index = 10;
                break;
            case 95:
                index = 11;
                break;
            case 100:
                index = 12;
                break;
            case 105:
                index = 13;
                break;
            case 110:
                index = 14;
                break;
            case 115:
                index = 15;
                break;
            case 120:
                index = 16;
                break;
            case 125:
                index = 17;
                break;
            case 130:
                index = 18;
                break;
            case 135:
                index = 19;
                break;
            case 140:
                index = 20;
                break;
            case 145:
                index = 21;
                break;
            case 150:
                index = 22;
                break;
            case 155:
                index = 23;
                break;
            case 160:
                index = 24;
                break;
            case 165:
                index = 25;
                break;
            case 170:
                index = 26;
                break;
            case 175:
                index = 27;
                break;
            case 180:
                index = 28;
                break;
            case 185:
                index = 29;
                break;
            case 190:
                index = 30;
                break;
            case 195:
                index = 31;
                break;
            case 200:
                index = 32;
                break;
            case 205:
                index = 33;
                break;
            case 210:
                index = 34;
                break;
            case 215:
                index = 35;
                break;
            case 220:
                index = 36;
                break;
            case 225:
                index = 37;
                break;
            case 230:
                index = 38;
                break;
            case 235:
                index = 39;
                break;
            case 240:
                index = 123;
                break;
            case 245:
                index = 41;
                break;
            case 250:
                index = 42;
                break;
            case 255:
                index = 43;
                break;
            case 5:
                index = 44;
                break;
            case 10:
                index = 45;
                break;
            case 15:
                index = 46;
                break;
            case 20:
                index = 47;
                break;
            case 25:
                index = 48;
                break;
            case 30:
                index = 49;
                break;
            case 35:
                index = 50;
                break;
            case 40:
                index = 51;
                break;
            case 44:
                index = 52;
                break;
            case 49:
                index = 53;
                break;
            case 54:
                index = 54;
                break;
            case 59:
                index = 55;
                break;
            case 64:
                index = 56;
                break;
            case 69:
                index = 57;
                break;
            case 74:
                index = 58;
                break;
            case 79:
                index = 59;
                break;
            case 84:
                index = 60;
                break;
            case 89:
                index = 61;
                break;
            case 94:
                index = 62;
                break;
            case 99:
                index = 63;
                break;
            case 104:
                index = 64;
                break;
            case 109:
                index = 65;
                break;
            case 114:
                index = 66;
                break;
            case 119:
                index = 67;
                break;
            case 124:
                index = 68;
                break;
            case 129:
                index = 69;
                break;
            case 134:
                index = 70;
                break;
            case 139:
                index = 71;
                break;
            case 144:
                index = 72;
                break;
            case 149:
                index = 73;
                break;
            case 154:
                index = 74;
                break;
            case 159:
                index = 75;
                break;
            case 164:
                index = 76;
                break;
            case 169:
                index = 77;
                break;
            case 174:
                index = 78;
                break;
            case 179:
                index = 79;
                break;
            case 184:
                index = 80;
                break;
            case 189:
                index = 81;
                break;
            case 194:
                index = 82;
                break;
            case 199:
                index = 83;
                break;
            case 204:
                index = 84;
                break;
            case 209:
                index = 85;
                break;
            case 214:
                index = 86;
                break;
            case 219:
                index = 87;
                break;
            case 224:
                index = 88;
                break;
            case 229:
                index = 89;
                break;
            case 234:
                index = 90;
                break;
            case 239:
                index = 91;
                break;
            case 244:
                index = 92;
                break;
            case 249:
                index = 93;
                break;
            case 254:
                index = 94;
                break;
            case 4:
                index = 95;
                break;
            case 9:
                index = 96;
                break;
            case 14:
                index = 97;
                break;
            case 19:
                index = 98;
                break;
            case 24:
                index = 99;
                break;
            case 29:
                index = 100;
                break;
            case 34:
                index = 101;
                break;
            case 39:
                index = 102;
                break;
            case 43:
                index = 103;
                break;
            case 48:
                index = 104;
                break;
            case 53:
                index = 105;
                break;
            case 58:
                index = 106;
                break;
            case 63:
                index = 107;
                break;
            case 68:
                index = 108;
                break;
            case 73:
                index = 109;
                break;
            case 78:
                index = 110;
                break;
            case 83:
                index = 111;
                break;
            case 88:
                index = 112;
                break;
            case 93:
                index = 113;
                break;
            case 98:
                index = 114;
                break;
            case 103:
                index = 115;
                break;
            case 108:
                index = 116;
                break;
            case 113:
                index = 117;
                break;
            case 118:
                index = 118;
                break;
            case 123:
                index = 119;
                break;
            case 128:
                index = 120;
                break;
            case 133:
                index = 121;
                break;
            case 138:
                index = 122;
                break;
            case 143:
                index = 40;
                break;
            case 148:
                index = 124;
                break;
            case 153:
                index = 125;
                break;
            case 158:
                index = 126;
                break;
            case 163:
                index = 127;
                break;
        }
        if(index==0){
            System.out.println("index 0 = x:"+this.posX+" y: "+this.posY);
        }
        return old!=index;
    }
    
    public static float[] centro(int index){
        float[] coordinate = new float[2];
        switch(index){
            case 1:
                coordinate[0]=630;
                coordinate[1]=3075;
                break;
            case 2:
                coordinate[0]=1000;
                coordinate[1]=3100;
                break;
            case 3:
                coordinate[0]=2025;
                coordinate[1]=3090;
                break;
            case 4:
                coordinate[0]=3050;
                coordinate[1]=2800;
                break;
            case 5:
                coordinate[0]=1000;
                coordinate[1]=2813;
                break;
            case 6:
                coordinate[0]=1225;
                coordinate[1]=2785;
                break;
            case 7:
                coordinate[0]=1315;
                coordinate[1]=2680;
                break;
            case 8:
                coordinate[0]=1490;
                coordinate[1]=2649;
                break;
            case 9:
                coordinate[0]=1470;
                coordinate[1]=2490;
                break;
            case 10:
                coordinate[0]=2000;
                coordinate[1]=2500;
                break;
            case 11:
                coordinate[0]=1460;
                coordinate[1]=2360;
                break;
            case 12:
                coordinate[0]=70;
                coordinate[1]=2540;
                break;
            case 13:
                coordinate[0]=2300;
                coordinate[1]=2630;
                break;
            case 14:
                coordinate[0]=30;
                coordinate[1]=3120;
                break;
            case 15:
                coordinate[0]=633;
                coordinate[1]=2900;
                break;
            case 16:
                coordinate[0]=637;
                coordinate[1]=2661;
                break;
            case 17:
                coordinate[0]=640;
                coordinate[1]=2537;
                break;
            case 18:
                coordinate[0]=650;
                coordinate[1]=2309;
                break;
            case 19:
                coordinate[0]=2460;
                coordinate[1]=2805;
                break;
            case 20:
                coordinate[0]=2450;
                coordinate[1]=2625;
                break;
            case 21:
                coordinate[0]=2450;
                coordinate[1]=2500;
                break;
            case 22:
                coordinate[0]=2545;
                coordinate[1]=1790;
                break;
            case 23:
                coordinate[0]=3040;
                coordinate[1]=2250;
                break;
            case 24:
                coordinate[0]=3180;
                coordinate[1]=1360;
                break;
            case 25:
                coordinate[0]=1335;
                coordinate[1]=2160;
                break;
            case 26:
                coordinate[0]=1100;
                coordinate[1]=2160;
                break;
            case 27:
                coordinate[0]=1137;
                coordinate[1]=2344;
                break;
            case 28:
                coordinate[0]=630;
                coordinate[1]=2150;
                break;
            case 29:
                coordinate[0]=2417;
                coordinate[1]=1280;
                break;
            case 30:
                coordinate[0]=2643;
                coordinate[1]=1824;
                break;
            case 31:
                coordinate[0]=2677;
                coordinate[1]=1684;
                break;
            case 32:
                coordinate[0]=2633;
                coordinate[1]=1468;
                break;
            case 33:
                coordinate[0]=2800;
                coordinate[1]=1440;
                break;
            case 34:
                coordinate[0]=2340;
                coordinate[1]=1800;
                break;
            case 35:
                coordinate[0]=2173;
                coordinate[1]=1464;
                break;
            case 36:
                coordinate[0]=1930;
                coordinate[1]=2264;
                break;
            case 37:
                coordinate[0]=1900;
                coordinate[1]=1350;
                break;
            case 38:
                coordinate[0]=3150;
                coordinate[1]=800;
                break;
            case 39:
                coordinate[0]=3110;
                coordinate[1]=300;
                break;
            case 40:
                coordinate[0]=2590;
                coordinate[1]=850;
                break;
            case 41:
                coordinate[0]=1852;
                coordinate[1]=810;
                break;
            case 42:
                coordinate[0]=1537;
                coordinate[1]=1765;
                break;
            case 43:
                coordinate[0]=1580;
                coordinate[1]=1641;
                break;
            case 44:
                coordinate[0]=1325;
                coordinate[1]=1485;
                break;
            case 45:
                coordinate[0]=1340;
                coordinate[1]=1370;
                break;
            case 46:
                coordinate[0]=50;
                coordinate[1]=1660;
                break;
            case 47:
                coordinate[0]=433;
                coordinate[1]=1490;
                break;
            case 48:
                coordinate[0]=409;
                coordinate[1]=1745;
                break;
            case 49:
                coordinate[0]=460;
                coordinate[1]=1362;
                break;
            case 50:
                coordinate[0]=580;
                coordinate[1]=1100;
                break;
            case 51:
                coordinate[0]=1100;
                coordinate[1]=1100;
                break;
            case 52:
                coordinate[0]=40;
                coordinate[1]=1100;
                break;
            case 53:
                coordinate[0]=250;
                coordinate[1]=710;
                break;
            case 54:
                coordinate[0]=500;
                coordinate[1]=700;
                break;
            case 55:
                coordinate[0]=105;
                coordinate[1]=335;
                break;
            case 56:
                coordinate[0]=560;
                coordinate[1]=320;
                break;
            case 57:
                coordinate[0]=105;
                coordinate[1]=83;
                break;
            case 58:
                coordinate[0]=1075;
                coordinate[1]=330;
                break;
            case 59:
                coordinate[0]=1490;
                coordinate[1]=101;
                break;
            case 60:
                coordinate[0]=1465;
                coordinate[1]=520;
                break;
            case 61:
                coordinate[0]=1300;
                coordinate[1]=510;
                break;
            case 62:
                coordinate[0]=1470;
                coordinate[1]=700;
                break;
            case 63:
                coordinate[0]=1390;
                coordinate[1]=970;
                break;
            case 64:
                coordinate[0]=270;
                coordinate[1]=3120;
                break;
            case 65:
                coordinate[0]=3060;
                coordinate[1]=3060;
                break;
            case 66:
                coordinate[0]=3030;
                coordinate[1]=2500;
                break;
            case 67:
                coordinate[0]=2544;
                coordinate[1]=2200;
                break;
            case 68:
                coordinate[0]=1860;
                coordinate[1]=1740;
                break;
            case 69:
                coordinate[0]=2794;
                coordinate[1]=1360;
                break;
            case 70:
                coordinate[0]=2194;
                coordinate[1]=1260;
                break;
            case 71:
                coordinate[0]=70;
                coordinate[1]=2111;
                break;
            case 72:
                coordinate[0]=1010;
                coordinate[1]=2500;
                break;
            case 73:
                coordinate[0]=1170;
                coordinate[1]=1920;
                break;
            case 74:
                coordinate[0]=50;
                coordinate[1]=730;
                break;
            case 75:
                coordinate[0]=600;
                coordinate[1]=550;
                break;
            case 76:
                coordinate[0]=2480;
                coordinate[1]=325;
                break;
            case 77:
                coordinate[0]=1675;
                coordinate[1]=620;
                break;
            case 78:
                coordinate[0]=2850;
                coordinate[1]=630;
                break;
            case 79:
                coordinate[0]=2825;
                coordinate[1]=755;
                break;
            case 80:
                coordinate[0]=2840;
                coordinate[1]=827;
                break;
            case 81:
                coordinate[0]=2915;
                coordinate[1]=920;
                break;
            case 82:
                coordinate[0]=2929;
                coordinate[1]=1077;
                break;
            case 83:
                coordinate[0]=2880;
                coordinate[1]=1150;
                break;
            case 84:
                coordinate[0]=2747;
                coordinate[1]=1050;
                break;
            case 85:
                coordinate[0]=2625;
                coordinate[1]=1150;
                break;
            case 86:
                coordinate[0]=2750;
                coordinate[1]=735;
                break;
            case 87:
                coordinate[0]=2654;
                coordinate[1]=730;
                break;
            case 88:
                coordinate[0]=2270;
                coordinate[1]=1056;
                break;
            case 89:
                coordinate[0]=2650;
                coordinate[1]=1010;
                break;
            case 90:
                coordinate[0]=2590;
                coordinate[1]=630;
                break;
            case 91:
                coordinate[0]=2455;
                coordinate[1]=731;
                break;
            case 92:
                coordinate[0]=2475;
                coordinate[1]=813;
                break;
            case 93:
                coordinate[0]=2365;
                coordinate[1]=741;
                break;
            case 94:
                coordinate[0]=2235;
                coordinate[1]=835;
                break;
            case 95:
                coordinate[0]=2300;
                coordinate[1]=940;
                break;
            case 96:
                coordinate[0]=2426;
                coordinate[1]=950;
                break;
            case 97:
                coordinate[0]=2428;
                coordinate[1]=1171;
                break;
            case 98:
                coordinate[0]=2180;
                coordinate[1]=1050;
                break;
            case 99:
                coordinate[0]=2170;
                coordinate[1]=1150;
                break;
            case 100:
                coordinate[0]=2050;
                coordinate[1]=1150;
                break;
            case 101:
                coordinate[0]=2050;
                coordinate[1]=990;
                break;
            case 102:
                coordinate[0]=2047;
                coordinate[1]=830;
                break;
            case 103:
                coordinate[0]=2050;
                coordinate[1]=630;
                break;
            case 104:
                coordinate[0]=2170;
                coordinate[1]=650;
                break;
            case 105:
                coordinate[0]=2275;
                coordinate[1]=640;
                break;
            case 106:
                coordinate[0]=2170;
                coordinate[1]=735;
                break;
            case 107:
                coordinate[0]=2270;
                coordinate[1]=730;
                break;
            case 108:
                coordinate[0]=1087;
                coordinate[1]=101;
                break;
            case 109:
                coordinate[0]=2850;
                coordinate[1]=513;
                break;
            case 110:
                coordinate[0]=2755;
                coordinate[1]=830;
                break;
            case 111:
                coordinate[0]=2530;
                coordinate[1]=1160;
                break;
            case 112:
                coordinate[0]=2470;
                coordinate[1]=620;
                break;
            case 113:
                coordinate[0]=714;
                coordinate[1]=885;
                break;
            case 114:
                coordinate[0]=715;
                coordinate[1]=720;
                break;
            case 115:
                coordinate[0]=715;
                coordinate[1]=530;
                break;
            case 116:
                coordinate[0]=560;
                coordinate[1]=900;
                break;
            case 117:
                coordinate[0]=1600;
                coordinate[1]=700;
                break;
            case 118:
                coordinate[0]=1930;
                coordinate[1]=510;
                break;
            case 119:
                coordinate[0]=70;
                coordinate[1]=1860;
                break;
            case 120:
                coordinate[0]=2940;
                coordinate[1]=733;
                break;
            case 121:
                coordinate[0]=2755;
                coordinate[1]=645;
                break;
            case 122:
                coordinate[0]=2370;
                coordinate[1]=625;
                break;
            case 123:
                coordinate[0]=2550;
                coordinate[1]=933;
                break;
            case 124:
                coordinate[0]=2590;
                coordinate[1]=735;
                break;
            case 125:
                coordinate[0]=2270;
                coordinate[1]=1140;
                break;
            case 126:
                coordinate[0]=1875;
                coordinate[1]=115;
                break;
            case 127:
                coordinate[0]=2140;
                coordinate[1]=995;
                break;
        }
       
        
        return coordinate;
    }
    
    public void setIndex(int index){
        this.index=index;
    }
    
    public int getIndex(){
        return index;
    }
}