package edu.upc.dsa.CRUD.util;

import java.util.HashMap;
import java.util.Objects;

public class QueryHelper {

    public static String createQueryINSERT(Object entity) {

        StringBuffer sb = new StringBuffer("INSERT INTO ");
        sb.append(entity.getClass().getSimpleName()).append(" ");
        sb.append("(");

        String [] fields = ObjectHelper.getFields(entity);

        sb.append("id");
        for (String field: fields) {
            sb.append(", ").append(field);
        }

        sb.append(") VALUES (?");

        for (String field: fields) {
            sb.append(", ?");
        }

        sb.append(")");

        return sb.toString();
    }

    public static String createQuerySELECT(Class theClass, String pk) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM ").append(theClass.getSimpleName());
        sb.append(" WHERE "+pk+"= ?");

        return sb.toString();
    }

    public static String createQuerySELECTAll(Class theClass) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM ").append(theClass.getSimpleName());
        return sb.toString();
    }

    public static String createQuerySELECTByName(Class theClass, String username){
        StringBuffer sb = new StringBuffer("");
        sb.append("SELECT * FROM ").append(theClass.getSimpleName());
        sb.append(" WHERE username = '").append(username).append("'");
        return sb.toString();
    }

    public static String createQuerySelectWithP(Class theClass, HashMap params) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM ").append(theClass.getSimpleName());
        sb.append(" WHERE (");

        params.forEach((k,v) ->{
            //k = k.substring(0, 1).toUpperCase() + k.substring(1);
            if(k.equals("password")){
                sb.append(k).append(" = MD5(").append("?").append(") AND ");
            }else {
                sb.append(k).append(" = ").append("?").append(" AND ");
            }
        });
        sb.delete(sb.length()-4, sb.length()-1);
        sb.append(")");

        return sb.toString();
    }
    public static String createQueryUPDATE(Class clase, String SET, String Where) {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(clase.getSimpleName());
        if (Objects.equals(SET, "PASSWORD")){
            sb.append(" SET ").append(SET);
            sb.append(" = MD5(?) ");
            sb.append(" WHERE ");
            sb.append(Where);
            sb.append(" = ?");
        }
        else{
            sb.append(" SET ").append(SET);
            sb.append(" = ? ");
            sb.append(" WHERE ");
            sb.append(Where);
            sb.append(" = ?");
        }
        return sb.toString();
    }
    public static String createQueryREUPDATE(Class clase, String SET, String Where, String Where2) {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(clase.getSimpleName());
        if (Objects.equals(SET, "PASSWORD")){
            sb.append(" SET ").append(SET);
            sb.append(" = MD5(?) ");
            sb.append(" WHERE ");
            sb.append(Where);
            sb.append(" = ?");
        }
        else{
            sb.append(" SET ").append(SET);
            sb.append(" = ? ");
            sb.append(" WHERE ");
            sb.append(Where);
            sb.append(" = ? ");
            sb.append(" AND ");
            sb.append(Where2);
            sb.append(" = ?");
        }
        return sb.toString();
    }


}

