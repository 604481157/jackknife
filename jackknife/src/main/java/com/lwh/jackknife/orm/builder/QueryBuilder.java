/*
 * Copyright (C) 2017 The JackKnife Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lwh.jackknife.orm.builder;

import com.lwh.jackknife.orm.Condition;

public class QueryBuilder {

    private static final String GROUP_BY = " GROUP BY ";

    private static final String HAVING = " HAVING ";

    private static final String ORDER_BY = " ORDER BY ";

    private static final String LIMIT = " LIMIT ";

    private static final String COMMA = ",";

    private String[] mColumns;

    private String mGroup;

    private String mHaving;

    private String mOrder;

    private String mLimit;

    private WhereBuilder mWhereBuilder;

    private QueryBuilder(){
        mWhereBuilder = WhereBuilder.create();
    }

    public QueryBuilder where(WhereBuilder builder){
        mWhereBuilder = builder;
        return this;
    }

    public QueryBuilder where(Condition condition){
        mWhereBuilder = WhereBuilder.create();
        mWhereBuilder.where(condition);
        return this;
    }

    public static QueryBuilder create(){
        return new QueryBuilder();
    }

    public QueryBuilder columns(String[] columns){
        mColumns = columns;
        return this;
    }

    public QueryBuilder having(String having){
        mHaving = HAVING + having;
        return this;
    }

    public QueryBuilder orderBy(String order){
        mOrder = ORDER_BY + order;
        return this;
    }

    public QueryBuilder groupBy(String group){
        mGroup = GROUP_BY + group;
        return this;
    }

    public QueryBuilder limit(String limit){
        mLimit = LIMIT + limit;
        return this;
    }

    public QueryBuilder limit(int start, int length){
        mLimit = LIMIT + start + COMMA + length;
        return this;
    }

    public WhereBuilder getWhereBuilder() {
        return mWhereBuilder;
    }

    public String getHaving() {
        return new StringBuilder(mHaving).delete(0, HAVING.length()-1).toString();
    }

    public String getOrder() {
        return new StringBuilder(mOrder).delete(0, ORDER_BY.length()-1).toString();
    }

    public String getGroup() {
        return new StringBuilder(mGroup).delete(0, GROUP_BY.length()-1).toString();
    }

    public String getLimit() {
        return new StringBuilder(mLimit).delete(0, LIMIT.length()-1).toString();
    }

    public String[] getColumns() {
        return mColumns;
    }
}
