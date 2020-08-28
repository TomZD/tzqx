package cn.movinginfo.tztf.sen.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TyphoonNewExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TyphoonNewExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andInserttimeIsNull() {
            addCriterion("inserttime is null");
            return (Criteria) this;
        }

        public Criteria andInserttimeIsNotNull() {
            addCriterion("inserttime is not null");
            return (Criteria) this;
        }

        public Criteria andInserttimeEqualTo(Date value) {
            addCriterion("inserttime =", value, "inserttime");
            return (Criteria) this;
        }

        public Criteria andInserttimeNotEqualTo(Date value) {
            addCriterion("inserttime <>", value, "inserttime");
            return (Criteria) this;
        }

        public Criteria andInserttimeGreaterThan(Date value) {
            addCriterion("inserttime >", value, "inserttime");
            return (Criteria) this;
        }

        public Criteria andInserttimeGreaterThanOrEqualTo(Date value) {
            addCriterion("inserttime >=", value, "inserttime");
            return (Criteria) this;
        }

        public Criteria andInserttimeLessThan(Date value) {
            addCriterion("inserttime <", value, "inserttime");
            return (Criteria) this;
        }

        public Criteria andInserttimeLessThanOrEqualTo(Date value) {
            addCriterion("inserttime <=", value, "inserttime");
            return (Criteria) this;
        }

        public Criteria andInserttimeIn(List<Date> values) {
            addCriterion("inserttime in", values, "inserttime");
            return (Criteria) this;
        }

        public Criteria andInserttimeNotIn(List<Date> values) {
            addCriterion("inserttime not in", values, "inserttime");
            return (Criteria) this;
        }

        public Criteria andInserttimeBetween(Date value1, Date value2) {
            addCriterion("inserttime between", value1, value2, "inserttime");
            return (Criteria) this;
        }

        public Criteria andInserttimeNotBetween(Date value1, Date value2) {
            addCriterion("inserttime not between", value1, value2, "inserttime");
            return (Criteria) this;
        }

        public Criteria andBianhaoIsNull() {
            addCriterion("bianhao is null");
            return (Criteria) this;
        }

        public Criteria andBianhaoIsNotNull() {
            addCriterion("bianhao is not null");
            return (Criteria) this;
        }

        public Criteria andBianhaoEqualTo(String value) {
            addCriterion("bianhao =", value, "bianhao");
            return (Criteria) this;
        }

        public Criteria andBianhaoNotEqualTo(String value) {
            addCriterion("bianhao <>", value, "bianhao");
            return (Criteria) this;
        }

        public Criteria andBianhaoGreaterThan(String value) {
            addCriterion("bianhao >", value, "bianhao");
            return (Criteria) this;
        }

        public Criteria andBianhaoGreaterThanOrEqualTo(String value) {
            addCriterion("bianhao >=", value, "bianhao");
            return (Criteria) this;
        }

        public Criteria andBianhaoLessThan(String value) {
            addCriterion("bianhao <", value, "bianhao");
            return (Criteria) this;
        }

        public Criteria andBianhaoLessThanOrEqualTo(String value) {
            addCriterion("bianhao <=", value, "bianhao");
            return (Criteria) this;
        }

        public Criteria andBianhaoLike(String value) {
            addCriterion("bianhao like", value, "bianhao");
            return (Criteria) this;
        }

        public Criteria andBianhaoNotLike(String value) {
            addCriterion("bianhao not like", value, "bianhao");
            return (Criteria) this;
        }

        public Criteria andBianhaoIn(List<String> values) {
            addCriterion("bianhao in", values, "bianhao");
            return (Criteria) this;
        }

        public Criteria andBianhaoNotIn(List<String> values) {
            addCriterion("bianhao not in", values, "bianhao");
            return (Criteria) this;
        }

        public Criteria andBianhaoBetween(String value1, String value2) {
            addCriterion("bianhao between", value1, value2, "bianhao");
            return (Criteria) this;
        }

        public Criteria andBianhaoNotBetween(String value1, String value2) {
            addCriterion("bianhao not between", value1, value2, "bianhao");
            return (Criteria) this;
        }

        public Criteria andFabuzheIsNull() {
            addCriterion("fabuzhe is null");
            return (Criteria) this;
        }

        public Criteria andFabuzheIsNotNull() {
            addCriterion("fabuzhe is not null");
            return (Criteria) this;
        }

        public Criteria andFabuzheEqualTo(String value) {
            addCriterion("fabuzhe =", value, "fabuzhe");
            return (Criteria) this;
        }

        public Criteria andFabuzheNotEqualTo(String value) {
            addCriterion("fabuzhe <>", value, "fabuzhe");
            return (Criteria) this;
        }

        public Criteria andFabuzheGreaterThan(String value) {
            addCriterion("fabuzhe >", value, "fabuzhe");
            return (Criteria) this;
        }

        public Criteria andFabuzheGreaterThanOrEqualTo(String value) {
            addCriterion("fabuzhe >=", value, "fabuzhe");
            return (Criteria) this;
        }

        public Criteria andFabuzheLessThan(String value) {
            addCriterion("fabuzhe <", value, "fabuzhe");
            return (Criteria) this;
        }

        public Criteria andFabuzheLessThanOrEqualTo(String value) {
            addCriterion("fabuzhe <=", value, "fabuzhe");
            return (Criteria) this;
        }

        public Criteria andFabuzheLike(String value) {
            addCriterion("fabuzhe like", value, "fabuzhe");
            return (Criteria) this;
        }

        public Criteria andFabuzheNotLike(String value) {
            addCriterion("fabuzhe not like", value, "fabuzhe");
            return (Criteria) this;
        }

        public Criteria andFabuzheIn(List<String> values) {
            addCriterion("fabuzhe in", values, "fabuzhe");
            return (Criteria) this;
        }

        public Criteria andFabuzheNotIn(List<String> values) {
            addCriterion("fabuzhe not in", values, "fabuzhe");
            return (Criteria) this;
        }

        public Criteria andFabuzheBetween(String value1, String value2) {
            addCriterion("fabuzhe between", value1, value2, "fabuzhe");
            return (Criteria) this;
        }

        public Criteria andFabuzheNotBetween(String value1, String value2) {
            addCriterion("fabuzhe not between", value1, value2, "fabuzhe");
            return (Criteria) this;
        }

        public Criteria andZhongwenbianhaoIsNull() {
            addCriterion("zhongwenbianhao is null");
            return (Criteria) this;
        }

        public Criteria andZhongwenbianhaoIsNotNull() {
            addCriterion("zhongwenbianhao is not null");
            return (Criteria) this;
        }

        public Criteria andZhongwenbianhaoEqualTo(String value) {
            addCriterion("zhongwenbianhao =", value, "zhongwenbianhao");
            return (Criteria) this;
        }

        public Criteria andZhongwenbianhaoNotEqualTo(String value) {
            addCriterion("zhongwenbianhao <>", value, "zhongwenbianhao");
            return (Criteria) this;
        }

        public Criteria andZhongwenbianhaoGreaterThan(String value) {
            addCriterion("zhongwenbianhao >", value, "zhongwenbianhao");
            return (Criteria) this;
        }

        public Criteria andZhongwenbianhaoGreaterThanOrEqualTo(String value) {
            addCriterion("zhongwenbianhao >=", value, "zhongwenbianhao");
            return (Criteria) this;
        }

        public Criteria andZhongwenbianhaoLessThan(String value) {
            addCriterion("zhongwenbianhao <", value, "zhongwenbianhao");
            return (Criteria) this;
        }

        public Criteria andZhongwenbianhaoLessThanOrEqualTo(String value) {
            addCriterion("zhongwenbianhao <=", value, "zhongwenbianhao");
            return (Criteria) this;
        }

        public Criteria andZhongwenbianhaoLike(String value) {
            addCriterion("zhongwenbianhao like", value, "zhongwenbianhao");
            return (Criteria) this;
        }

        public Criteria andZhongwenbianhaoNotLike(String value) {
            addCriterion("zhongwenbianhao not like", value, "zhongwenbianhao");
            return (Criteria) this;
        }

        public Criteria andZhongwenbianhaoIn(List<String> values) {
            addCriterion("zhongwenbianhao in", values, "zhongwenbianhao");
            return (Criteria) this;
        }

        public Criteria andZhongwenbianhaoNotIn(List<String> values) {
            addCriterion("zhongwenbianhao not in", values, "zhongwenbianhao");
            return (Criteria) this;
        }

        public Criteria andZhongwenbianhaoBetween(String value1, String value2) {
            addCriterion("zhongwenbianhao between", value1, value2, "zhongwenbianhao");
            return (Criteria) this;
        }

        public Criteria andZhongwenbianhaoNotBetween(String value1, String value2) {
            addCriterion("zhongwenbianhao not between", value1, value2, "zhongwenbianhao");
            return (Criteria) this;
        }

        public Criteria andGoujibianhaoIsNull() {
            addCriterion("goujibianhao is null");
            return (Criteria) this;
        }

        public Criteria andGoujibianhaoIsNotNull() {
            addCriterion("goujibianhao is not null");
            return (Criteria) this;
        }

        public Criteria andGoujibianhaoEqualTo(String value) {
            addCriterion("goujibianhao =", value, "goujibianhao");
            return (Criteria) this;
        }

        public Criteria andGoujibianhaoNotEqualTo(String value) {
            addCriterion("goujibianhao <>", value, "goujibianhao");
            return (Criteria) this;
        }

        public Criteria andGoujibianhaoGreaterThan(String value) {
            addCriterion("goujibianhao >", value, "goujibianhao");
            return (Criteria) this;
        }

        public Criteria andGoujibianhaoGreaterThanOrEqualTo(String value) {
            addCriterion("goujibianhao >=", value, "goujibianhao");
            return (Criteria) this;
        }

        public Criteria andGoujibianhaoLessThan(String value) {
            addCriterion("goujibianhao <", value, "goujibianhao");
            return (Criteria) this;
        }

        public Criteria andGoujibianhaoLessThanOrEqualTo(String value) {
            addCriterion("goujibianhao <=", value, "goujibianhao");
            return (Criteria) this;
        }

        public Criteria andGoujibianhaoLike(String value) {
            addCriterion("goujibianhao like", value, "goujibianhao");
            return (Criteria) this;
        }

        public Criteria andGoujibianhaoNotLike(String value) {
            addCriterion("goujibianhao not like", value, "goujibianhao");
            return (Criteria) this;
        }

        public Criteria andGoujibianhaoIn(List<String> values) {
            addCriterion("goujibianhao in", values, "goujibianhao");
            return (Criteria) this;
        }

        public Criteria andGoujibianhaoNotIn(List<String> values) {
            addCriterion("goujibianhao not in", values, "goujibianhao");
            return (Criteria) this;
        }

        public Criteria andGoujibianhaoBetween(String value1, String value2) {
            addCriterion("goujibianhao between", value1, value2, "goujibianhao");
            return (Criteria) this;
        }

        public Criteria andGoujibianhaoNotBetween(String value1, String value2) {
            addCriterion("goujibianhao not between", value1, value2, "goujibianhao");
            return (Criteria) this;
        }

        public Criteria andXianzaishijianIsNull() {
            addCriterion("xianzaishijian is null");
            return (Criteria) this;
        }

        public Criteria andXianzaishijianIsNotNull() {
            addCriterion("xianzaishijian is not null");
            return (Criteria) this;
        }

        public Criteria andXianzaishijianEqualTo(String value) {
            addCriterion("xianzaishijian =", value, "xianzaishijian");
            return (Criteria) this;
        }

        public Criteria andXianzaishijianNotEqualTo(String value) {
            addCriterion("xianzaishijian <>", value, "xianzaishijian");
            return (Criteria) this;
        }

        public Criteria andXianzaishijianGreaterThan(String value) {
            addCriterion("xianzaishijian >", value, "xianzaishijian");
            return (Criteria) this;
        }

        public Criteria andXianzaishijianGreaterThanOrEqualTo(String value) {
            addCriterion("xianzaishijian >=", value, "xianzaishijian");
            return (Criteria) this;
        }

        public Criteria andXianzaishijianLessThan(String value) {
            addCriterion("xianzaishijian <", value, "xianzaishijian");
            return (Criteria) this;
        }

        public Criteria andXianzaishijianLessThanOrEqualTo(String value) {
            addCriterion("xianzaishijian <=", value, "xianzaishijian");
            return (Criteria) this;
        }

        public Criteria andXianzaishijianLike(String value) {
            addCriterion("xianzaishijian like", value, "xianzaishijian");
            return (Criteria) this;
        }

        public Criteria andXianzaishijianNotLike(String value) {
            addCriterion("xianzaishijian not like", value, "xianzaishijian");
            return (Criteria) this;
        }

        public Criteria andXianzaishijianIn(List<String> values) {
            addCriterion("xianzaishijian in", values, "xianzaishijian");
            return (Criteria) this;
        }

        public Criteria andXianzaishijianNotIn(List<String> values) {
            addCriterion("xianzaishijian not in", values, "xianzaishijian");
            return (Criteria) this;
        }

        public Criteria andXianzaishijianBetween(String value1, String value2) {
            addCriterion("xianzaishijian between", value1, value2, "xianzaishijian");
            return (Criteria) this;
        }

        public Criteria andXianzaishijianNotBetween(String value1, String value2) {
            addCriterion("xianzaishijian not between", value1, value2, "xianzaishijian");
            return (Criteria) this;
        }

        public Criteria andYubaoshixiaoIsNull() {
            addCriterion("yubaoshixiao is null");
            return (Criteria) this;
        }

        public Criteria andYubaoshixiaoIsNotNull() {
            addCriterion("yubaoshixiao is not null");
            return (Criteria) this;
        }

        public Criteria andYubaoshixiaoEqualTo(String value) {
            addCriterion("yubaoshixiao =", value, "yubaoshixiao");
            return (Criteria) this;
        }

        public Criteria andYubaoshixiaoNotEqualTo(String value) {
            addCriterion("yubaoshixiao <>", value, "yubaoshixiao");
            return (Criteria) this;
        }

        public Criteria andYubaoshixiaoGreaterThan(String value) {
            addCriterion("yubaoshixiao >", value, "yubaoshixiao");
            return (Criteria) this;
        }

        public Criteria andYubaoshixiaoGreaterThanOrEqualTo(String value) {
            addCriterion("yubaoshixiao >=", value, "yubaoshixiao");
            return (Criteria) this;
        }

        public Criteria andYubaoshixiaoLessThan(String value) {
            addCriterion("yubaoshixiao <", value, "yubaoshixiao");
            return (Criteria) this;
        }

        public Criteria andYubaoshixiaoLessThanOrEqualTo(String value) {
            addCriterion("yubaoshixiao <=", value, "yubaoshixiao");
            return (Criteria) this;
        }

        public Criteria andYubaoshixiaoLike(String value) {
            addCriterion("yubaoshixiao like", value, "yubaoshixiao");
            return (Criteria) this;
        }

        public Criteria andYubaoshixiaoNotLike(String value) {
            addCriterion("yubaoshixiao not like", value, "yubaoshixiao");
            return (Criteria) this;
        }

        public Criteria andYubaoshixiaoIn(List<String> values) {
            addCriterion("yubaoshixiao in", values, "yubaoshixiao");
            return (Criteria) this;
        }

        public Criteria andYubaoshixiaoNotIn(List<String> values) {
            addCriterion("yubaoshixiao not in", values, "yubaoshixiao");
            return (Criteria) this;
        }

        public Criteria andYubaoshixiaoBetween(String value1, String value2) {
            addCriterion("yubaoshixiao between", value1, value2, "yubaoshixiao");
            return (Criteria) this;
        }

        public Criteria andYubaoshixiaoNotBetween(String value1, String value2) {
            addCriterion("yubaoshixiao not between", value1, value2, "yubaoshixiao");
            return (Criteria) this;
        }

        public Criteria andYubaoshijianIsNull() {
            addCriterion("yubaoshijian is null");
            return (Criteria) this;
        }

        public Criteria andYubaoshijianIsNotNull() {
            addCriterion("yubaoshijian is not null");
            return (Criteria) this;
        }

        public Criteria andYubaoshijianEqualTo(String value) {
            addCriterion("yubaoshijian =", value, "yubaoshijian");
            return (Criteria) this;
        }

        public Criteria andYubaoshijianNotEqualTo(String value) {
            addCriterion("yubaoshijian <>", value, "yubaoshijian");
            return (Criteria) this;
        }

        public Criteria andYubaoshijianGreaterThan(String value) {
            addCriterion("yubaoshijian >", value, "yubaoshijian");
            return (Criteria) this;
        }

        public Criteria andYubaoshijianGreaterThanOrEqualTo(String value) {
            addCriterion("yubaoshijian >=", value, "yubaoshijian");
            return (Criteria) this;
        }

        public Criteria andYubaoshijianLessThan(String value) {
            addCriterion("yubaoshijian <", value, "yubaoshijian");
            return (Criteria) this;
        }

        public Criteria andYubaoshijianLessThanOrEqualTo(String value) {
            addCriterion("yubaoshijian <=", value, "yubaoshijian");
            return (Criteria) this;
        }

        public Criteria andYubaoshijianLike(String value) {
            addCriterion("yubaoshijian like", value, "yubaoshijian");
            return (Criteria) this;
        }

        public Criteria andYubaoshijianNotLike(String value) {
            addCriterion("yubaoshijian not like", value, "yubaoshijian");
            return (Criteria) this;
        }

        public Criteria andYubaoshijianIn(List<String> values) {
            addCriterion("yubaoshijian in", values, "yubaoshijian");
            return (Criteria) this;
        }

        public Criteria andYubaoshijianNotIn(List<String> values) {
            addCriterion("yubaoshijian not in", values, "yubaoshijian");
            return (Criteria) this;
        }

        public Criteria andYubaoshijianBetween(String value1, String value2) {
            addCriterion("yubaoshijian between", value1, value2, "yubaoshijian");
            return (Criteria) this;
        }

        public Criteria andYubaoshijianNotBetween(String value1, String value2) {
            addCriterion("yubaoshijian not between", value1, value2, "yubaoshijian");
            return (Criteria) this;
        }

        public Criteria andXianzaiweiduIsNull() {
            addCriterion("xianzaiweidu is null");
            return (Criteria) this;
        }

        public Criteria andXianzaiweiduIsNotNull() {
            addCriterion("xianzaiweidu is not null");
            return (Criteria) this;
        }

        public Criteria andXianzaiweiduEqualTo(String value) {
            addCriterion("xianzaiweidu =", value, "xianzaiweidu");
            return (Criteria) this;
        }

        public Criteria andXianzaiweiduNotEqualTo(String value) {
            addCriterion("xianzaiweidu <>", value, "xianzaiweidu");
            return (Criteria) this;
        }

        public Criteria andXianzaiweiduGreaterThan(String value) {
            addCriterion("xianzaiweidu >", value, "xianzaiweidu");
            return (Criteria) this;
        }

        public Criteria andXianzaiweiduGreaterThanOrEqualTo(String value) {
            addCriterion("xianzaiweidu >=", value, "xianzaiweidu");
            return (Criteria) this;
        }

        public Criteria andXianzaiweiduLessThan(String value) {
            addCriterion("xianzaiweidu <", value, "xianzaiweidu");
            return (Criteria) this;
        }

        public Criteria andXianzaiweiduLessThanOrEqualTo(String value) {
            addCriterion("xianzaiweidu <=", value, "xianzaiweidu");
            return (Criteria) this;
        }

        public Criteria andXianzaiweiduLike(String value) {
            addCriterion("xianzaiweidu like", value, "xianzaiweidu");
            return (Criteria) this;
        }

        public Criteria andXianzaiweiduNotLike(String value) {
            addCriterion("xianzaiweidu not like", value, "xianzaiweidu");
            return (Criteria) this;
        }

        public Criteria andXianzaiweiduIn(List<String> values) {
            addCriterion("xianzaiweidu in", values, "xianzaiweidu");
            return (Criteria) this;
        }

        public Criteria andXianzaiweiduNotIn(List<String> values) {
            addCriterion("xianzaiweidu not in", values, "xianzaiweidu");
            return (Criteria) this;
        }

        public Criteria andXianzaiweiduBetween(String value1, String value2) {
            addCriterion("xianzaiweidu between", value1, value2, "xianzaiweidu");
            return (Criteria) this;
        }

        public Criteria andXianzaiweiduNotBetween(String value1, String value2) {
            addCriterion("xianzaiweidu not between", value1, value2, "xianzaiweidu");
            return (Criteria) this;
        }

        public Criteria andXianzaijinduIsNull() {
            addCriterion("xianzaijindu is null");
            return (Criteria) this;
        }

        public Criteria andXianzaijinduIsNotNull() {
            addCriterion("xianzaijindu is not null");
            return (Criteria) this;
        }

        public Criteria andXianzaijinduEqualTo(String value) {
            addCriterion("xianzaijindu =", value, "xianzaijindu");
            return (Criteria) this;
        }

        public Criteria andXianzaijinduNotEqualTo(String value) {
            addCriterion("xianzaijindu <>", value, "xianzaijindu");
            return (Criteria) this;
        }

        public Criteria andXianzaijinduGreaterThan(String value) {
            addCriterion("xianzaijindu >", value, "xianzaijindu");
            return (Criteria) this;
        }

        public Criteria andXianzaijinduGreaterThanOrEqualTo(String value) {
            addCriterion("xianzaijindu >=", value, "xianzaijindu");
            return (Criteria) this;
        }

        public Criteria andXianzaijinduLessThan(String value) {
            addCriterion("xianzaijindu <", value, "xianzaijindu");
            return (Criteria) this;
        }

        public Criteria andXianzaijinduLessThanOrEqualTo(String value) {
            addCriterion("xianzaijindu <=", value, "xianzaijindu");
            return (Criteria) this;
        }

        public Criteria andXianzaijinduLike(String value) {
            addCriterion("xianzaijindu like", value, "xianzaijindu");
            return (Criteria) this;
        }

        public Criteria andXianzaijinduNotLike(String value) {
            addCriterion("xianzaijindu not like", value, "xianzaijindu");
            return (Criteria) this;
        }

        public Criteria andXianzaijinduIn(List<String> values) {
            addCriterion("xianzaijindu in", values, "xianzaijindu");
            return (Criteria) this;
        }

        public Criteria andXianzaijinduNotIn(List<String> values) {
            addCriterion("xianzaijindu not in", values, "xianzaijindu");
            return (Criteria) this;
        }

        public Criteria andXianzaijinduBetween(String value1, String value2) {
            addCriterion("xianzaijindu between", value1, value2, "xianzaijindu");
            return (Criteria) this;
        }

        public Criteria andXianzaijinduNotBetween(String value1, String value2) {
            addCriterion("xianzaijindu not between", value1, value2, "xianzaijindu");
            return (Criteria) this;
        }

        public Criteria andXianzaifengliIsNull() {
            addCriterion("xianzaifengli is null");
            return (Criteria) this;
        }

        public Criteria andXianzaifengliIsNotNull() {
            addCriterion("xianzaifengli is not null");
            return (Criteria) this;
        }

        public Criteria andXianzaifengliEqualTo(String value) {
            addCriterion("xianzaifengli =", value, "xianzaifengli");
            return (Criteria) this;
        }

        public Criteria andXianzaifengliNotEqualTo(String value) {
            addCriterion("xianzaifengli <>", value, "xianzaifengli");
            return (Criteria) this;
        }

        public Criteria andXianzaifengliGreaterThan(String value) {
            addCriterion("xianzaifengli >", value, "xianzaifengli");
            return (Criteria) this;
        }

        public Criteria andXianzaifengliGreaterThanOrEqualTo(String value) {
            addCriterion("xianzaifengli >=", value, "xianzaifengli");
            return (Criteria) this;
        }

        public Criteria andXianzaifengliLessThan(String value) {
            addCriterion("xianzaifengli <", value, "xianzaifengli");
            return (Criteria) this;
        }

        public Criteria andXianzaifengliLessThanOrEqualTo(String value) {
            addCriterion("xianzaifengli <=", value, "xianzaifengli");
            return (Criteria) this;
        }

        public Criteria andXianzaifengliLike(String value) {
            addCriterion("xianzaifengli like", value, "xianzaifengli");
            return (Criteria) this;
        }

        public Criteria andXianzaifengliNotLike(String value) {
            addCriterion("xianzaifengli not like", value, "xianzaifengli");
            return (Criteria) this;
        }

        public Criteria andXianzaifengliIn(List<String> values) {
            addCriterion("xianzaifengli in", values, "xianzaifengli");
            return (Criteria) this;
        }

        public Criteria andXianzaifengliNotIn(List<String> values) {
            addCriterion("xianzaifengli not in", values, "xianzaifengli");
            return (Criteria) this;
        }

        public Criteria andXianzaifengliBetween(String value1, String value2) {
            addCriterion("xianzaifengli between", value1, value2, "xianzaifengli");
            return (Criteria) this;
        }

        public Criteria andXianzaifengliNotBetween(String value1, String value2) {
            addCriterion("xianzaifengli not between", value1, value2, "xianzaifengli");
            return (Criteria) this;
        }

        public Criteria andXianzaiqiyaIsNull() {
            addCriterion("xianzaiqiya is null");
            return (Criteria) this;
        }

        public Criteria andXianzaiqiyaIsNotNull() {
            addCriterion("xianzaiqiya is not null");
            return (Criteria) this;
        }

        public Criteria andXianzaiqiyaEqualTo(String value) {
            addCriterion("xianzaiqiya =", value, "xianzaiqiya");
            return (Criteria) this;
        }

        public Criteria andXianzaiqiyaNotEqualTo(String value) {
            addCriterion("xianzaiqiya <>", value, "xianzaiqiya");
            return (Criteria) this;
        }

        public Criteria andXianzaiqiyaGreaterThan(String value) {
            addCriterion("xianzaiqiya >", value, "xianzaiqiya");
            return (Criteria) this;
        }

        public Criteria andXianzaiqiyaGreaterThanOrEqualTo(String value) {
            addCriterion("xianzaiqiya >=", value, "xianzaiqiya");
            return (Criteria) this;
        }

        public Criteria andXianzaiqiyaLessThan(String value) {
            addCriterion("xianzaiqiya <", value, "xianzaiqiya");
            return (Criteria) this;
        }

        public Criteria andXianzaiqiyaLessThanOrEqualTo(String value) {
            addCriterion("xianzaiqiya <=", value, "xianzaiqiya");
            return (Criteria) this;
        }

        public Criteria andXianzaiqiyaLike(String value) {
            addCriterion("xianzaiqiya like", value, "xianzaiqiya");
            return (Criteria) this;
        }

        public Criteria andXianzaiqiyaNotLike(String value) {
            addCriterion("xianzaiqiya not like", value, "xianzaiqiya");
            return (Criteria) this;
        }

        public Criteria andXianzaiqiyaIn(List<String> values) {
            addCriterion("xianzaiqiya in", values, "xianzaiqiya");
            return (Criteria) this;
        }

        public Criteria andXianzaiqiyaNotIn(List<String> values) {
            addCriterion("xianzaiqiya not in", values, "xianzaiqiya");
            return (Criteria) this;
        }

        public Criteria andXianzaiqiyaBetween(String value1, String value2) {
            addCriterion("xianzaiqiya between", value1, value2, "xianzaiqiya");
            return (Criteria) this;
        }

        public Criteria andXianzaiqiyaNotBetween(String value1, String value2) {
            addCriterion("xianzaiqiya not between", value1, value2, "xianzaiqiya");
            return (Criteria) this;
        }

        public Criteria andTaifengjibieIsNull() {
            addCriterion("taifengjibie is null");
            return (Criteria) this;
        }

        public Criteria andTaifengjibieIsNotNull() {
            addCriterion("taifengjibie is not null");
            return (Criteria) this;
        }

        public Criteria andTaifengjibieEqualTo(String value) {
            addCriterion("taifengjibie =", value, "taifengjibie");
            return (Criteria) this;
        }

        public Criteria andTaifengjibieNotEqualTo(String value) {
            addCriterion("taifengjibie <>", value, "taifengjibie");
            return (Criteria) this;
        }

        public Criteria andTaifengjibieGreaterThan(String value) {
            addCriterion("taifengjibie >", value, "taifengjibie");
            return (Criteria) this;
        }

        public Criteria andTaifengjibieGreaterThanOrEqualTo(String value) {
            addCriterion("taifengjibie >=", value, "taifengjibie");
            return (Criteria) this;
        }

        public Criteria andTaifengjibieLessThan(String value) {
            addCriterion("taifengjibie <", value, "taifengjibie");
            return (Criteria) this;
        }

        public Criteria andTaifengjibieLessThanOrEqualTo(String value) {
            addCriterion("taifengjibie <=", value, "taifengjibie");
            return (Criteria) this;
        }

        public Criteria andTaifengjibieLike(String value) {
            addCriterion("taifengjibie like", value, "taifengjibie");
            return (Criteria) this;
        }

        public Criteria andTaifengjibieNotLike(String value) {
            addCriterion("taifengjibie not like", value, "taifengjibie");
            return (Criteria) this;
        }

        public Criteria andTaifengjibieIn(List<String> values) {
            addCriterion("taifengjibie in", values, "taifengjibie");
            return (Criteria) this;
        }

        public Criteria andTaifengjibieNotIn(List<String> values) {
            addCriterion("taifengjibie not in", values, "taifengjibie");
            return (Criteria) this;
        }

        public Criteria andTaifengjibieBetween(String value1, String value2) {
            addCriterion("taifengjibie between", value1, value2, "taifengjibie");
            return (Criteria) this;
        }

        public Criteria andTaifengjibieNotBetween(String value1, String value2) {
            addCriterion("taifengjibie not between", value1, value2, "taifengjibie");
            return (Criteria) this;
        }

        public Criteria andFenglifor7IsNull() {
            addCriterion("fenglifor7 is null");
            return (Criteria) this;
        }

        public Criteria andFenglifor7IsNotNull() {
            addCriterion("fenglifor7 is not null");
            return (Criteria) this;
        }

        public Criteria andFenglifor7EqualTo(String value) {
            addCriterion("fenglifor7 =", value, "fenglifor7");
            return (Criteria) this;
        }

        public Criteria andFenglifor7NotEqualTo(String value) {
            addCriterion("fenglifor7 <>", value, "fenglifor7");
            return (Criteria) this;
        }

        public Criteria andFenglifor7GreaterThan(String value) {
            addCriterion("fenglifor7 >", value, "fenglifor7");
            return (Criteria) this;
        }

        public Criteria andFenglifor7GreaterThanOrEqualTo(String value) {
            addCriterion("fenglifor7 >=", value, "fenglifor7");
            return (Criteria) this;
        }

        public Criteria andFenglifor7LessThan(String value) {
            addCriterion("fenglifor7 <", value, "fenglifor7");
            return (Criteria) this;
        }

        public Criteria andFenglifor7LessThanOrEqualTo(String value) {
            addCriterion("fenglifor7 <=", value, "fenglifor7");
            return (Criteria) this;
        }

        public Criteria andFenglifor7Like(String value) {
            addCriterion("fenglifor7 like", value, "fenglifor7");
            return (Criteria) this;
        }

        public Criteria andFenglifor7NotLike(String value) {
            addCriterion("fenglifor7 not like", value, "fenglifor7");
            return (Criteria) this;
        }

        public Criteria andFenglifor7In(List<String> values) {
            addCriterion("fenglifor7 in", values, "fenglifor7");
            return (Criteria) this;
        }

        public Criteria andFenglifor7NotIn(List<String> values) {
            addCriterion("fenglifor7 not in", values, "fenglifor7");
            return (Criteria) this;
        }

        public Criteria andFenglifor7Between(String value1, String value2) {
            addCriterion("fenglifor7 between", value1, value2, "fenglifor7");
            return (Criteria) this;
        }

        public Criteria andFenglifor7NotBetween(String value1, String value2) {
            addCriterion("fenglifor7 not between", value1, value2, "fenglifor7");
            return (Criteria) this;
        }

        public Criteria andFenglifor7NortheastIsNull() {
            addCriterion("fenglifor7_northeast is null");
            return (Criteria) this;
        }

        public Criteria andFenglifor7NortheastIsNotNull() {
            addCriterion("fenglifor7_northeast is not null");
            return (Criteria) this;
        }

        public Criteria andFenglifor7NortheastEqualTo(String value) {
            addCriterion("fenglifor7_northeast =", value, "fenglifor7Northeast");
            return (Criteria) this;
        }

        public Criteria andFenglifor7NortheastNotEqualTo(String value) {
            addCriterion("fenglifor7_northeast <>", value, "fenglifor7Northeast");
            return (Criteria) this;
        }

        public Criteria andFenglifor7NortheastGreaterThan(String value) {
            addCriterion("fenglifor7_northeast >", value, "fenglifor7Northeast");
            return (Criteria) this;
        }

        public Criteria andFenglifor7NortheastGreaterThanOrEqualTo(String value) {
            addCriterion("fenglifor7_northeast >=", value, "fenglifor7Northeast");
            return (Criteria) this;
        }

        public Criteria andFenglifor7NortheastLessThan(String value) {
            addCriterion("fenglifor7_northeast <", value, "fenglifor7Northeast");
            return (Criteria) this;
        }

        public Criteria andFenglifor7NortheastLessThanOrEqualTo(String value) {
            addCriterion("fenglifor7_northeast <=", value, "fenglifor7Northeast");
            return (Criteria) this;
        }

        public Criteria andFenglifor7NortheastLike(String value) {
            addCriterion("fenglifor7_northeast like", value, "fenglifor7Northeast");
            return (Criteria) this;
        }

        public Criteria andFenglifor7NortheastNotLike(String value) {
            addCriterion("fenglifor7_northeast not like", value, "fenglifor7Northeast");
            return (Criteria) this;
        }

        public Criteria andFenglifor7NortheastIn(List<String> values) {
            addCriterion("fenglifor7_northeast in", values, "fenglifor7Northeast");
            return (Criteria) this;
        }

        public Criteria andFenglifor7NortheastNotIn(List<String> values) {
            addCriterion("fenglifor7_northeast not in", values, "fenglifor7Northeast");
            return (Criteria) this;
        }

        public Criteria andFenglifor7NortheastBetween(String value1, String value2) {
            addCriterion("fenglifor7_northeast between", value1, value2, "fenglifor7Northeast");
            return (Criteria) this;
        }

        public Criteria andFenglifor7NortheastNotBetween(String value1, String value2) {
            addCriterion("fenglifor7_northeast not between", value1, value2, "fenglifor7Northeast");
            return (Criteria) this;
        }

        public Criteria andFenglifor7SoutheastIsNull() {
            addCriterion("fenglifor7_southeast is null");
            return (Criteria) this;
        }

        public Criteria andFenglifor7SoutheastIsNotNull() {
            addCriterion("fenglifor7_southeast is not null");
            return (Criteria) this;
        }

        public Criteria andFenglifor7SoutheastEqualTo(String value) {
            addCriterion("fenglifor7_southeast =", value, "fenglifor7Southeast");
            return (Criteria) this;
        }

        public Criteria andFenglifor7SoutheastNotEqualTo(String value) {
            addCriterion("fenglifor7_southeast <>", value, "fenglifor7Southeast");
            return (Criteria) this;
        }

        public Criteria andFenglifor7SoutheastGreaterThan(String value) {
            addCriterion("fenglifor7_southeast >", value, "fenglifor7Southeast");
            return (Criteria) this;
        }

        public Criteria andFenglifor7SoutheastGreaterThanOrEqualTo(String value) {
            addCriterion("fenglifor7_southeast >=", value, "fenglifor7Southeast");
            return (Criteria) this;
        }

        public Criteria andFenglifor7SoutheastLessThan(String value) {
            addCriterion("fenglifor7_southeast <", value, "fenglifor7Southeast");
            return (Criteria) this;
        }

        public Criteria andFenglifor7SoutheastLessThanOrEqualTo(String value) {
            addCriterion("fenglifor7_southeast <=", value, "fenglifor7Southeast");
            return (Criteria) this;
        }

        public Criteria andFenglifor7SoutheastLike(String value) {
            addCriterion("fenglifor7_southeast like", value, "fenglifor7Southeast");
            return (Criteria) this;
        }

        public Criteria andFenglifor7SoutheastNotLike(String value) {
            addCriterion("fenglifor7_southeast not like", value, "fenglifor7Southeast");
            return (Criteria) this;
        }

        public Criteria andFenglifor7SoutheastIn(List<String> values) {
            addCriterion("fenglifor7_southeast in", values, "fenglifor7Southeast");
            return (Criteria) this;
        }

        public Criteria andFenglifor7SoutheastNotIn(List<String> values) {
            addCriterion("fenglifor7_southeast not in", values, "fenglifor7Southeast");
            return (Criteria) this;
        }

        public Criteria andFenglifor7SoutheastBetween(String value1, String value2) {
            addCriterion("fenglifor7_southeast between", value1, value2, "fenglifor7Southeast");
            return (Criteria) this;
        }

        public Criteria andFenglifor7SoutheastNotBetween(String value1, String value2) {
            addCriterion("fenglifor7_southeast not between", value1, value2, "fenglifor7Southeast");
            return (Criteria) this;
        }

        public Criteria andFenglifor7SouthwestIsNull() {
            addCriterion("fenglifor7_southwest is null");
            return (Criteria) this;
        }

        public Criteria andFenglifor7SouthwestIsNotNull() {
            addCriterion("fenglifor7_southwest is not null");
            return (Criteria) this;
        }

        public Criteria andFenglifor7SouthwestEqualTo(String value) {
            addCriterion("fenglifor7_southwest =", value, "fenglifor7Southwest");
            return (Criteria) this;
        }

        public Criteria andFenglifor7SouthwestNotEqualTo(String value) {
            addCriterion("fenglifor7_southwest <>", value, "fenglifor7Southwest");
            return (Criteria) this;
        }

        public Criteria andFenglifor7SouthwestGreaterThan(String value) {
            addCriterion("fenglifor7_southwest >", value, "fenglifor7Southwest");
            return (Criteria) this;
        }

        public Criteria andFenglifor7SouthwestGreaterThanOrEqualTo(String value) {
            addCriterion("fenglifor7_southwest >=", value, "fenglifor7Southwest");
            return (Criteria) this;
        }

        public Criteria andFenglifor7SouthwestLessThan(String value) {
            addCriterion("fenglifor7_southwest <", value, "fenglifor7Southwest");
            return (Criteria) this;
        }

        public Criteria andFenglifor7SouthwestLessThanOrEqualTo(String value) {
            addCriterion("fenglifor7_southwest <=", value, "fenglifor7Southwest");
            return (Criteria) this;
        }

        public Criteria andFenglifor7SouthwestLike(String value) {
            addCriterion("fenglifor7_southwest like", value, "fenglifor7Southwest");
            return (Criteria) this;
        }

        public Criteria andFenglifor7SouthwestNotLike(String value) {
            addCriterion("fenglifor7_southwest not like", value, "fenglifor7Southwest");
            return (Criteria) this;
        }

        public Criteria andFenglifor7SouthwestIn(List<String> values) {
            addCriterion("fenglifor7_southwest in", values, "fenglifor7Southwest");
            return (Criteria) this;
        }

        public Criteria andFenglifor7SouthwestNotIn(List<String> values) {
            addCriterion("fenglifor7_southwest not in", values, "fenglifor7Southwest");
            return (Criteria) this;
        }

        public Criteria andFenglifor7SouthwestBetween(String value1, String value2) {
            addCriterion("fenglifor7_southwest between", value1, value2, "fenglifor7Southwest");
            return (Criteria) this;
        }

        public Criteria andFenglifor7SouthwestNotBetween(String value1, String value2) {
            addCriterion("fenglifor7_southwest not between", value1, value2, "fenglifor7Southwest");
            return (Criteria) this;
        }

        public Criteria andFenglifor7NorthwestIsNull() {
            addCriterion("fenglifor7_northwest is null");
            return (Criteria) this;
        }

        public Criteria andFenglifor7NorthwestIsNotNull() {
            addCriterion("fenglifor7_northwest is not null");
            return (Criteria) this;
        }

        public Criteria andFenglifor7NorthwestEqualTo(String value) {
            addCriterion("fenglifor7_northwest =", value, "fenglifor7Northwest");
            return (Criteria) this;
        }

        public Criteria andFenglifor7NorthwestNotEqualTo(String value) {
            addCriterion("fenglifor7_northwest <>", value, "fenglifor7Northwest");
            return (Criteria) this;
        }

        public Criteria andFenglifor7NorthwestGreaterThan(String value) {
            addCriterion("fenglifor7_northwest >", value, "fenglifor7Northwest");
            return (Criteria) this;
        }

        public Criteria andFenglifor7NorthwestGreaterThanOrEqualTo(String value) {
            addCriterion("fenglifor7_northwest >=", value, "fenglifor7Northwest");
            return (Criteria) this;
        }

        public Criteria andFenglifor7NorthwestLessThan(String value) {
            addCriterion("fenglifor7_northwest <", value, "fenglifor7Northwest");
            return (Criteria) this;
        }

        public Criteria andFenglifor7NorthwestLessThanOrEqualTo(String value) {
            addCriterion("fenglifor7_northwest <=", value, "fenglifor7Northwest");
            return (Criteria) this;
        }

        public Criteria andFenglifor7NorthwestLike(String value) {
            addCriterion("fenglifor7_northwest like", value, "fenglifor7Northwest");
            return (Criteria) this;
        }

        public Criteria andFenglifor7NorthwestNotLike(String value) {
            addCriterion("fenglifor7_northwest not like", value, "fenglifor7Northwest");
            return (Criteria) this;
        }

        public Criteria andFenglifor7NorthwestIn(List<String> values) {
            addCriterion("fenglifor7_northwest in", values, "fenglifor7Northwest");
            return (Criteria) this;
        }

        public Criteria andFenglifor7NorthwestNotIn(List<String> values) {
            addCriterion("fenglifor7_northwest not in", values, "fenglifor7Northwest");
            return (Criteria) this;
        }

        public Criteria andFenglifor7NorthwestBetween(String value1, String value2) {
            addCriterion("fenglifor7_northwest between", value1, value2, "fenglifor7Northwest");
            return (Criteria) this;
        }

        public Criteria andFenglifor7NorthwestNotBetween(String value1, String value2) {
            addCriterion("fenglifor7_northwest not between", value1, value2, "fenglifor7Northwest");
            return (Criteria) this;
        }

        public Criteria andFenglifor10IsNull() {
            addCriterion("fenglifor10 is null");
            return (Criteria) this;
        }

        public Criteria andFenglifor10IsNotNull() {
            addCriterion("fenglifor10 is not null");
            return (Criteria) this;
        }

        public Criteria andFenglifor10EqualTo(String value) {
            addCriterion("fenglifor10 =", value, "fenglifor10");
            return (Criteria) this;
        }

        public Criteria andFenglifor10NotEqualTo(String value) {
            addCriterion("fenglifor10 <>", value, "fenglifor10");
            return (Criteria) this;
        }

        public Criteria andFenglifor10GreaterThan(String value) {
            addCriterion("fenglifor10 >", value, "fenglifor10");
            return (Criteria) this;
        }

        public Criteria andFenglifor10GreaterThanOrEqualTo(String value) {
            addCriterion("fenglifor10 >=", value, "fenglifor10");
            return (Criteria) this;
        }

        public Criteria andFenglifor10LessThan(String value) {
            addCriterion("fenglifor10 <", value, "fenglifor10");
            return (Criteria) this;
        }

        public Criteria andFenglifor10LessThanOrEqualTo(String value) {
            addCriterion("fenglifor10 <=", value, "fenglifor10");
            return (Criteria) this;
        }

        public Criteria andFenglifor10Like(String value) {
            addCriterion("fenglifor10 like", value, "fenglifor10");
            return (Criteria) this;
        }

        public Criteria andFenglifor10NotLike(String value) {
            addCriterion("fenglifor10 not like", value, "fenglifor10");
            return (Criteria) this;
        }

        public Criteria andFenglifor10In(List<String> values) {
            addCriterion("fenglifor10 in", values, "fenglifor10");
            return (Criteria) this;
        }

        public Criteria andFenglifor10NotIn(List<String> values) {
            addCriterion("fenglifor10 not in", values, "fenglifor10");
            return (Criteria) this;
        }

        public Criteria andFenglifor10Between(String value1, String value2) {
            addCriterion("fenglifor10 between", value1, value2, "fenglifor10");
            return (Criteria) this;
        }

        public Criteria andFenglifor10NotBetween(String value1, String value2) {
            addCriterion("fenglifor10 not between", value1, value2, "fenglifor10");
            return (Criteria) this;
        }

        public Criteria andFenglifor10NortheastIsNull() {
            addCriterion("fenglifor10_northeast is null");
            return (Criteria) this;
        }

        public Criteria andFenglifor10NortheastIsNotNull() {
            addCriterion("fenglifor10_northeast is not null");
            return (Criteria) this;
        }

        public Criteria andFenglifor10NortheastEqualTo(String value) {
            addCriterion("fenglifor10_northeast =", value, "fenglifor10Northeast");
            return (Criteria) this;
        }

        public Criteria andFenglifor10NortheastNotEqualTo(String value) {
            addCriterion("fenglifor10_northeast <>", value, "fenglifor10Northeast");
            return (Criteria) this;
        }

        public Criteria andFenglifor10NortheastGreaterThan(String value) {
            addCriterion("fenglifor10_northeast >", value, "fenglifor10Northeast");
            return (Criteria) this;
        }

        public Criteria andFenglifor10NortheastGreaterThanOrEqualTo(String value) {
            addCriterion("fenglifor10_northeast >=", value, "fenglifor10Northeast");
            return (Criteria) this;
        }

        public Criteria andFenglifor10NortheastLessThan(String value) {
            addCriterion("fenglifor10_northeast <", value, "fenglifor10Northeast");
            return (Criteria) this;
        }

        public Criteria andFenglifor10NortheastLessThanOrEqualTo(String value) {
            addCriterion("fenglifor10_northeast <=", value, "fenglifor10Northeast");
            return (Criteria) this;
        }

        public Criteria andFenglifor10NortheastLike(String value) {
            addCriterion("fenglifor10_northeast like", value, "fenglifor10Northeast");
            return (Criteria) this;
        }

        public Criteria andFenglifor10NortheastNotLike(String value) {
            addCriterion("fenglifor10_northeast not like", value, "fenglifor10Northeast");
            return (Criteria) this;
        }

        public Criteria andFenglifor10NortheastIn(List<String> values) {
            addCriterion("fenglifor10_northeast in", values, "fenglifor10Northeast");
            return (Criteria) this;
        }

        public Criteria andFenglifor10NortheastNotIn(List<String> values) {
            addCriterion("fenglifor10_northeast not in", values, "fenglifor10Northeast");
            return (Criteria) this;
        }

        public Criteria andFenglifor10NortheastBetween(String value1, String value2) {
            addCriterion("fenglifor10_northeast between", value1, value2, "fenglifor10Northeast");
            return (Criteria) this;
        }

        public Criteria andFenglifor10NortheastNotBetween(String value1, String value2) {
            addCriterion("fenglifor10_northeast not between", value1, value2, "fenglifor10Northeast");
            return (Criteria) this;
        }

        public Criteria andFenglifor10SoutheastIsNull() {
            addCriterion("fenglifor10_southeast is null");
            return (Criteria) this;
        }

        public Criteria andFenglifor10SoutheastIsNotNull() {
            addCriterion("fenglifor10_southeast is not null");
            return (Criteria) this;
        }

        public Criteria andFenglifor10SoutheastEqualTo(String value) {
            addCriterion("fenglifor10_southeast =", value, "fenglifor10Southeast");
            return (Criteria) this;
        }

        public Criteria andFenglifor10SoutheastNotEqualTo(String value) {
            addCriterion("fenglifor10_southeast <>", value, "fenglifor10Southeast");
            return (Criteria) this;
        }

        public Criteria andFenglifor10SoutheastGreaterThan(String value) {
            addCriterion("fenglifor10_southeast >", value, "fenglifor10Southeast");
            return (Criteria) this;
        }

        public Criteria andFenglifor10SoutheastGreaterThanOrEqualTo(String value) {
            addCriterion("fenglifor10_southeast >=", value, "fenglifor10Southeast");
            return (Criteria) this;
        }

        public Criteria andFenglifor10SoutheastLessThan(String value) {
            addCriterion("fenglifor10_southeast <", value, "fenglifor10Southeast");
            return (Criteria) this;
        }

        public Criteria andFenglifor10SoutheastLessThanOrEqualTo(String value) {
            addCriterion("fenglifor10_southeast <=", value, "fenglifor10Southeast");
            return (Criteria) this;
        }

        public Criteria andFenglifor10SoutheastLike(String value) {
            addCriterion("fenglifor10_southeast like", value, "fenglifor10Southeast");
            return (Criteria) this;
        }

        public Criteria andFenglifor10SoutheastNotLike(String value) {
            addCriterion("fenglifor10_southeast not like", value, "fenglifor10Southeast");
            return (Criteria) this;
        }

        public Criteria andFenglifor10SoutheastIn(List<String> values) {
            addCriterion("fenglifor10_southeast in", values, "fenglifor10Southeast");
            return (Criteria) this;
        }

        public Criteria andFenglifor10SoutheastNotIn(List<String> values) {
            addCriterion("fenglifor10_southeast not in", values, "fenglifor10Southeast");
            return (Criteria) this;
        }

        public Criteria andFenglifor10SoutheastBetween(String value1, String value2) {
            addCriterion("fenglifor10_southeast between", value1, value2, "fenglifor10Southeast");
            return (Criteria) this;
        }

        public Criteria andFenglifor10SoutheastNotBetween(String value1, String value2) {
            addCriterion("fenglifor10_southeast not between", value1, value2, "fenglifor10Southeast");
            return (Criteria) this;
        }

        public Criteria andFenglifor10SouthwestIsNull() {
            addCriterion("fenglifor10_southwest is null");
            return (Criteria) this;
        }

        public Criteria andFenglifor10SouthwestIsNotNull() {
            addCriterion("fenglifor10_southwest is not null");
            return (Criteria) this;
        }

        public Criteria andFenglifor10SouthwestEqualTo(String value) {
            addCriterion("fenglifor10_southwest =", value, "fenglifor10Southwest");
            return (Criteria) this;
        }

        public Criteria andFenglifor10SouthwestNotEqualTo(String value) {
            addCriterion("fenglifor10_southwest <>", value, "fenglifor10Southwest");
            return (Criteria) this;
        }

        public Criteria andFenglifor10SouthwestGreaterThan(String value) {
            addCriterion("fenglifor10_southwest >", value, "fenglifor10Southwest");
            return (Criteria) this;
        }

        public Criteria andFenglifor10SouthwestGreaterThanOrEqualTo(String value) {
            addCriterion("fenglifor10_southwest >=", value, "fenglifor10Southwest");
            return (Criteria) this;
        }

        public Criteria andFenglifor10SouthwestLessThan(String value) {
            addCriterion("fenglifor10_southwest <", value, "fenglifor10Southwest");
            return (Criteria) this;
        }

        public Criteria andFenglifor10SouthwestLessThanOrEqualTo(String value) {
            addCriterion("fenglifor10_southwest <=", value, "fenglifor10Southwest");
            return (Criteria) this;
        }

        public Criteria andFenglifor10SouthwestLike(String value) {
            addCriterion("fenglifor10_southwest like", value, "fenglifor10Southwest");
            return (Criteria) this;
        }

        public Criteria andFenglifor10SouthwestNotLike(String value) {
            addCriterion("fenglifor10_southwest not like", value, "fenglifor10Southwest");
            return (Criteria) this;
        }

        public Criteria andFenglifor10SouthwestIn(List<String> values) {
            addCriterion("fenglifor10_southwest in", values, "fenglifor10Southwest");
            return (Criteria) this;
        }

        public Criteria andFenglifor10SouthwestNotIn(List<String> values) {
            addCriterion("fenglifor10_southwest not in", values, "fenglifor10Southwest");
            return (Criteria) this;
        }

        public Criteria andFenglifor10SouthwestBetween(String value1, String value2) {
            addCriterion("fenglifor10_southwest between", value1, value2, "fenglifor10Southwest");
            return (Criteria) this;
        }

        public Criteria andFenglifor10SouthwestNotBetween(String value1, String value2) {
            addCriterion("fenglifor10_southwest not between", value1, value2, "fenglifor10Southwest");
            return (Criteria) this;
        }

        public Criteria andFenglifor10NorthwestIsNull() {
            addCriterion("fenglifor10_northwest is null");
            return (Criteria) this;
        }

        public Criteria andFenglifor10NorthwestIsNotNull() {
            addCriterion("fenglifor10_northwest is not null");
            return (Criteria) this;
        }

        public Criteria andFenglifor10NorthwestEqualTo(String value) {
            addCriterion("fenglifor10_northwest =", value, "fenglifor10Northwest");
            return (Criteria) this;
        }

        public Criteria andFenglifor10NorthwestNotEqualTo(String value) {
            addCriterion("fenglifor10_northwest <>", value, "fenglifor10Northwest");
            return (Criteria) this;
        }

        public Criteria andFenglifor10NorthwestGreaterThan(String value) {
            addCriterion("fenglifor10_northwest >", value, "fenglifor10Northwest");
            return (Criteria) this;
        }

        public Criteria andFenglifor10NorthwestGreaterThanOrEqualTo(String value) {
            addCriterion("fenglifor10_northwest >=", value, "fenglifor10Northwest");
            return (Criteria) this;
        }

        public Criteria andFenglifor10NorthwestLessThan(String value) {
            addCriterion("fenglifor10_northwest <", value, "fenglifor10Northwest");
            return (Criteria) this;
        }

        public Criteria andFenglifor10NorthwestLessThanOrEqualTo(String value) {
            addCriterion("fenglifor10_northwest <=", value, "fenglifor10Northwest");
            return (Criteria) this;
        }

        public Criteria andFenglifor10NorthwestLike(String value) {
            addCriterion("fenglifor10_northwest like", value, "fenglifor10Northwest");
            return (Criteria) this;
        }

        public Criteria andFenglifor10NorthwestNotLike(String value) {
            addCriterion("fenglifor10_northwest not like", value, "fenglifor10Northwest");
            return (Criteria) this;
        }

        public Criteria andFenglifor10NorthwestIn(List<String> values) {
            addCriterion("fenglifor10_northwest in", values, "fenglifor10Northwest");
            return (Criteria) this;
        }

        public Criteria andFenglifor10NorthwestNotIn(List<String> values) {
            addCriterion("fenglifor10_northwest not in", values, "fenglifor10Northwest");
            return (Criteria) this;
        }

        public Criteria andFenglifor10NorthwestBetween(String value1, String value2) {
            addCriterion("fenglifor10_northwest between", value1, value2, "fenglifor10Northwest");
            return (Criteria) this;
        }

        public Criteria andFenglifor10NorthwestNotBetween(String value1, String value2) {
            addCriterion("fenglifor10_northwest not between", value1, value2, "fenglifor10Northwest");
            return (Criteria) this;
        }

        public Criteria andFenglifor12NortheastIsNull() {
            addCriterion("fenglifor12_northeast is null");
            return (Criteria) this;
        }

        public Criteria andFenglifor12NortheastIsNotNull() {
            addCriterion("fenglifor12_northeast is not null");
            return (Criteria) this;
        }

        public Criteria andFenglifor12NortheastEqualTo(String value) {
            addCriterion("fenglifor12_northeast =", value, "fenglifor12Northeast");
            return (Criteria) this;
        }

        public Criteria andFenglifor12NortheastNotEqualTo(String value) {
            addCriterion("fenglifor12_northeast <>", value, "fenglifor12Northeast");
            return (Criteria) this;
        }

        public Criteria andFenglifor12NortheastGreaterThan(String value) {
            addCriterion("fenglifor12_northeast >", value, "fenglifor12Northeast");
            return (Criteria) this;
        }

        public Criteria andFenglifor12NortheastGreaterThanOrEqualTo(String value) {
            addCriterion("fenglifor12_northeast >=", value, "fenglifor12Northeast");
            return (Criteria) this;
        }

        public Criteria andFenglifor12NortheastLessThan(String value) {
            addCriterion("fenglifor12_northeast <", value, "fenglifor12Northeast");
            return (Criteria) this;
        }

        public Criteria andFenglifor12NortheastLessThanOrEqualTo(String value) {
            addCriterion("fenglifor12_northeast <=", value, "fenglifor12Northeast");
            return (Criteria) this;
        }

        public Criteria andFenglifor12NortheastLike(String value) {
            addCriterion("fenglifor12_northeast like", value, "fenglifor12Northeast");
            return (Criteria) this;
        }

        public Criteria andFenglifor12NortheastNotLike(String value) {
            addCriterion("fenglifor12_northeast not like", value, "fenglifor12Northeast");
            return (Criteria) this;
        }

        public Criteria andFenglifor12NortheastIn(List<String> values) {
            addCriterion("fenglifor12_northeast in", values, "fenglifor12Northeast");
            return (Criteria) this;
        }

        public Criteria andFenglifor12NortheastNotIn(List<String> values) {
            addCriterion("fenglifor12_northeast not in", values, "fenglifor12Northeast");
            return (Criteria) this;
        }

        public Criteria andFenglifor12NortheastBetween(String value1, String value2) {
            addCriterion("fenglifor12_northeast between", value1, value2, "fenglifor12Northeast");
            return (Criteria) this;
        }

        public Criteria andFenglifor12NortheastNotBetween(String value1, String value2) {
            addCriterion("fenglifor12_northeast not between", value1, value2, "fenglifor12Northeast");
            return (Criteria) this;
        }

        public Criteria andFenglifor12SoutheastIsNull() {
            addCriterion("fenglifor12_southeast is null");
            return (Criteria) this;
        }

        public Criteria andFenglifor12SoutheastIsNotNull() {
            addCriterion("fenglifor12_southeast is not null");
            return (Criteria) this;
        }

        public Criteria andFenglifor12SoutheastEqualTo(String value) {
            addCriterion("fenglifor12_southeast =", value, "fenglifor12Southeast");
            return (Criteria) this;
        }

        public Criteria andFenglifor12SoutheastNotEqualTo(String value) {
            addCriterion("fenglifor12_southeast <>", value, "fenglifor12Southeast");
            return (Criteria) this;
        }

        public Criteria andFenglifor12SoutheastGreaterThan(String value) {
            addCriterion("fenglifor12_southeast >", value, "fenglifor12Southeast");
            return (Criteria) this;
        }

        public Criteria andFenglifor12SoutheastGreaterThanOrEqualTo(String value) {
            addCriterion("fenglifor12_southeast >=", value, "fenglifor12Southeast");
            return (Criteria) this;
        }

        public Criteria andFenglifor12SoutheastLessThan(String value) {
            addCriterion("fenglifor12_southeast <", value, "fenglifor12Southeast");
            return (Criteria) this;
        }

        public Criteria andFenglifor12SoutheastLessThanOrEqualTo(String value) {
            addCriterion("fenglifor12_southeast <=", value, "fenglifor12Southeast");
            return (Criteria) this;
        }

        public Criteria andFenglifor12SoutheastLike(String value) {
            addCriterion("fenglifor12_southeast like", value, "fenglifor12Southeast");
            return (Criteria) this;
        }

        public Criteria andFenglifor12SoutheastNotLike(String value) {
            addCriterion("fenglifor12_southeast not like", value, "fenglifor12Southeast");
            return (Criteria) this;
        }

        public Criteria andFenglifor12SoutheastIn(List<String> values) {
            addCriterion("fenglifor12_southeast in", values, "fenglifor12Southeast");
            return (Criteria) this;
        }

        public Criteria andFenglifor12SoutheastNotIn(List<String> values) {
            addCriterion("fenglifor12_southeast not in", values, "fenglifor12Southeast");
            return (Criteria) this;
        }

        public Criteria andFenglifor12SoutheastBetween(String value1, String value2) {
            addCriterion("fenglifor12_southeast between", value1, value2, "fenglifor12Southeast");
            return (Criteria) this;
        }

        public Criteria andFenglifor12SoutheastNotBetween(String value1, String value2) {
            addCriterion("fenglifor12_southeast not between", value1, value2, "fenglifor12Southeast");
            return (Criteria) this;
        }

        public Criteria andFenglifor12SouthwestIsNull() {
            addCriterion("fenglifor12_southwest is null");
            return (Criteria) this;
        }

        public Criteria andFenglifor12SouthwestIsNotNull() {
            addCriterion("fenglifor12_southwest is not null");
            return (Criteria) this;
        }

        public Criteria andFenglifor12SouthwestEqualTo(String value) {
            addCriterion("fenglifor12_southwest =", value, "fenglifor12Southwest");
            return (Criteria) this;
        }

        public Criteria andFenglifor12SouthwestNotEqualTo(String value) {
            addCriterion("fenglifor12_southwest <>", value, "fenglifor12Southwest");
            return (Criteria) this;
        }

        public Criteria andFenglifor12SouthwestGreaterThan(String value) {
            addCriterion("fenglifor12_southwest >", value, "fenglifor12Southwest");
            return (Criteria) this;
        }

        public Criteria andFenglifor12SouthwestGreaterThanOrEqualTo(String value) {
            addCriterion("fenglifor12_southwest >=", value, "fenglifor12Southwest");
            return (Criteria) this;
        }

        public Criteria andFenglifor12SouthwestLessThan(String value) {
            addCriterion("fenglifor12_southwest <", value, "fenglifor12Southwest");
            return (Criteria) this;
        }

        public Criteria andFenglifor12SouthwestLessThanOrEqualTo(String value) {
            addCriterion("fenglifor12_southwest <=", value, "fenglifor12Southwest");
            return (Criteria) this;
        }

        public Criteria andFenglifor12SouthwestLike(String value) {
            addCriterion("fenglifor12_southwest like", value, "fenglifor12Southwest");
            return (Criteria) this;
        }

        public Criteria andFenglifor12SouthwestNotLike(String value) {
            addCriterion("fenglifor12_southwest not like", value, "fenglifor12Southwest");
            return (Criteria) this;
        }

        public Criteria andFenglifor12SouthwestIn(List<String> values) {
            addCriterion("fenglifor12_southwest in", values, "fenglifor12Southwest");
            return (Criteria) this;
        }

        public Criteria andFenglifor12SouthwestNotIn(List<String> values) {
            addCriterion("fenglifor12_southwest not in", values, "fenglifor12Southwest");
            return (Criteria) this;
        }

        public Criteria andFenglifor12SouthwestBetween(String value1, String value2) {
            addCriterion("fenglifor12_southwest between", value1, value2, "fenglifor12Southwest");
            return (Criteria) this;
        }

        public Criteria andFenglifor12SouthwestNotBetween(String value1, String value2) {
            addCriterion("fenglifor12_southwest not between", value1, value2, "fenglifor12Southwest");
            return (Criteria) this;
        }

        public Criteria andFenglifor12NorthwestIsNull() {
            addCriterion("fenglifor12_northwest is null");
            return (Criteria) this;
        }

        public Criteria andFenglifor12NorthwestIsNotNull() {
            addCriterion("fenglifor12_northwest is not null");
            return (Criteria) this;
        }

        public Criteria andFenglifor12NorthwestEqualTo(String value) {
            addCriterion("fenglifor12_northwest =", value, "fenglifor12Northwest");
            return (Criteria) this;
        }

        public Criteria andFenglifor12NorthwestNotEqualTo(String value) {
            addCriterion("fenglifor12_northwest <>", value, "fenglifor12Northwest");
            return (Criteria) this;
        }

        public Criteria andFenglifor12NorthwestGreaterThan(String value) {
            addCriterion("fenglifor12_northwest >", value, "fenglifor12Northwest");
            return (Criteria) this;
        }

        public Criteria andFenglifor12NorthwestGreaterThanOrEqualTo(String value) {
            addCriterion("fenglifor12_northwest >=", value, "fenglifor12Northwest");
            return (Criteria) this;
        }

        public Criteria andFenglifor12NorthwestLessThan(String value) {
            addCriterion("fenglifor12_northwest <", value, "fenglifor12Northwest");
            return (Criteria) this;
        }

        public Criteria andFenglifor12NorthwestLessThanOrEqualTo(String value) {
            addCriterion("fenglifor12_northwest <=", value, "fenglifor12Northwest");
            return (Criteria) this;
        }

        public Criteria andFenglifor12NorthwestLike(String value) {
            addCriterion("fenglifor12_northwest like", value, "fenglifor12Northwest");
            return (Criteria) this;
        }

        public Criteria andFenglifor12NorthwestNotLike(String value) {
            addCriterion("fenglifor12_northwest not like", value, "fenglifor12Northwest");
            return (Criteria) this;
        }

        public Criteria andFenglifor12NorthwestIn(List<String> values) {
            addCriterion("fenglifor12_northwest in", values, "fenglifor12Northwest");
            return (Criteria) this;
        }

        public Criteria andFenglifor12NorthwestNotIn(List<String> values) {
            addCriterion("fenglifor12_northwest not in", values, "fenglifor12Northwest");
            return (Criteria) this;
        }

        public Criteria andFenglifor12NorthwestBetween(String value1, String value2) {
            addCriterion("fenglifor12_northwest between", value1, value2, "fenglifor12Northwest");
            return (Criteria) this;
        }

        public Criteria andFenglifor12NorthwestNotBetween(String value1, String value2) {
            addCriterion("fenglifor12_northwest not between", value1, value2, "fenglifor12Northwest");
            return (Criteria) this;
        }

        public Criteria andYidongfangxiangIsNull() {
            addCriterion("yidongfangxiang is null");
            return (Criteria) this;
        }

        public Criteria andYidongfangxiangIsNotNull() {
            addCriterion("yidongfangxiang is not null");
            return (Criteria) this;
        }

        public Criteria andYidongfangxiangEqualTo(String value) {
            addCriterion("yidongfangxiang =", value, "yidongfangxiang");
            return (Criteria) this;
        }

        public Criteria andYidongfangxiangNotEqualTo(String value) {
            addCriterion("yidongfangxiang <>", value, "yidongfangxiang");
            return (Criteria) this;
        }

        public Criteria andYidongfangxiangGreaterThan(String value) {
            addCriterion("yidongfangxiang >", value, "yidongfangxiang");
            return (Criteria) this;
        }

        public Criteria andYidongfangxiangGreaterThanOrEqualTo(String value) {
            addCriterion("yidongfangxiang >=", value, "yidongfangxiang");
            return (Criteria) this;
        }

        public Criteria andYidongfangxiangLessThan(String value) {
            addCriterion("yidongfangxiang <", value, "yidongfangxiang");
            return (Criteria) this;
        }

        public Criteria andYidongfangxiangLessThanOrEqualTo(String value) {
            addCriterion("yidongfangxiang <=", value, "yidongfangxiang");
            return (Criteria) this;
        }

        public Criteria andYidongfangxiangLike(String value) {
            addCriterion("yidongfangxiang like", value, "yidongfangxiang");
            return (Criteria) this;
        }

        public Criteria andYidongfangxiangNotLike(String value) {
            addCriterion("yidongfangxiang not like", value, "yidongfangxiang");
            return (Criteria) this;
        }

        public Criteria andYidongfangxiangIn(List<String> values) {
            addCriterion("yidongfangxiang in", values, "yidongfangxiang");
            return (Criteria) this;
        }

        public Criteria andYidongfangxiangNotIn(List<String> values) {
            addCriterion("yidongfangxiang not in", values, "yidongfangxiang");
            return (Criteria) this;
        }

        public Criteria andYidongfangxiangBetween(String value1, String value2) {
            addCriterion("yidongfangxiang between", value1, value2, "yidongfangxiang");
            return (Criteria) this;
        }

        public Criteria andYidongfangxiangNotBetween(String value1, String value2) {
            addCriterion("yidongfangxiang not between", value1, value2, "yidongfangxiang");
            return (Criteria) this;
        }

        public Criteria andYidongsuduIsNull() {
            addCriterion("yidongsudu is null");
            return (Criteria) this;
        }

        public Criteria andYidongsuduIsNotNull() {
            addCriterion("yidongsudu is not null");
            return (Criteria) this;
        }

        public Criteria andYidongsuduEqualTo(String value) {
            addCriterion("yidongsudu =", value, "yidongsudu");
            return (Criteria) this;
        }

        public Criteria andYidongsuduNotEqualTo(String value) {
            addCriterion("yidongsudu <>", value, "yidongsudu");
            return (Criteria) this;
        }

        public Criteria andYidongsuduGreaterThan(String value) {
            addCriterion("yidongsudu >", value, "yidongsudu");
            return (Criteria) this;
        }

        public Criteria andYidongsuduGreaterThanOrEqualTo(String value) {
            addCriterion("yidongsudu >=", value, "yidongsudu");
            return (Criteria) this;
        }

        public Criteria andYidongsuduLessThan(String value) {
            addCriterion("yidongsudu <", value, "yidongsudu");
            return (Criteria) this;
        }

        public Criteria andYidongsuduLessThanOrEqualTo(String value) {
            addCriterion("yidongsudu <=", value, "yidongsudu");
            return (Criteria) this;
        }

        public Criteria andYidongsuduLike(String value) {
            addCriterion("yidongsudu like", value, "yidongsudu");
            return (Criteria) this;
        }

        public Criteria andYidongsuduNotLike(String value) {
            addCriterion("yidongsudu not like", value, "yidongsudu");
            return (Criteria) this;
        }

        public Criteria andYidongsuduIn(List<String> values) {
            addCriterion("yidongsudu in", values, "yidongsudu");
            return (Criteria) this;
        }

        public Criteria andYidongsuduNotIn(List<String> values) {
            addCriterion("yidongsudu not in", values, "yidongsudu");
            return (Criteria) this;
        }

        public Criteria andYidongsuduBetween(String value1, String value2) {
            addCriterion("yidongsudu between", value1, value2, "yidongsudu");
            return (Criteria) this;
        }

        public Criteria andYidongsuduNotBetween(String value1, String value2) {
            addCriterion("yidongsudu not between", value1, value2, "yidongsudu");
            return (Criteria) this;
        }

        public Criteria andYubaojinduIsNull() {
            addCriterion("yubaojindu is null");
            return (Criteria) this;
        }

        public Criteria andYubaojinduIsNotNull() {
            addCriterion("yubaojindu is not null");
            return (Criteria) this;
        }

        public Criteria andYubaojinduEqualTo(String value) {
            addCriterion("yubaojindu =", value, "yubaojindu");
            return (Criteria) this;
        }

        public Criteria andYubaojinduNotEqualTo(String value) {
            addCriterion("yubaojindu <>", value, "yubaojindu");
            return (Criteria) this;
        }

        public Criteria andYubaojinduGreaterThan(String value) {
            addCriterion("yubaojindu >", value, "yubaojindu");
            return (Criteria) this;
        }

        public Criteria andYubaojinduGreaterThanOrEqualTo(String value) {
            addCriterion("yubaojindu >=", value, "yubaojindu");
            return (Criteria) this;
        }

        public Criteria andYubaojinduLessThan(String value) {
            addCriterion("yubaojindu <", value, "yubaojindu");
            return (Criteria) this;
        }

        public Criteria andYubaojinduLessThanOrEqualTo(String value) {
            addCriterion("yubaojindu <=", value, "yubaojindu");
            return (Criteria) this;
        }

        public Criteria andYubaojinduLike(String value) {
            addCriterion("yubaojindu like", value, "yubaojindu");
            return (Criteria) this;
        }

        public Criteria andYubaojinduNotLike(String value) {
            addCriterion("yubaojindu not like", value, "yubaojindu");
            return (Criteria) this;
        }

        public Criteria andYubaojinduIn(List<String> values) {
            addCriterion("yubaojindu in", values, "yubaojindu");
            return (Criteria) this;
        }

        public Criteria andYubaojinduNotIn(List<String> values) {
            addCriterion("yubaojindu not in", values, "yubaojindu");
            return (Criteria) this;
        }

        public Criteria andYubaojinduBetween(String value1, String value2) {
            addCriterion("yubaojindu between", value1, value2, "yubaojindu");
            return (Criteria) this;
        }

        public Criteria andYubaojinduNotBetween(String value1, String value2) {
            addCriterion("yubaojindu not between", value1, value2, "yubaojindu");
            return (Criteria) this;
        }

        public Criteria andYubaoweiduIsNull() {
            addCriterion("yubaoweidu is null");
            return (Criteria) this;
        }

        public Criteria andYubaoweiduIsNotNull() {
            addCriterion("yubaoweidu is not null");
            return (Criteria) this;
        }

        public Criteria andYubaoweiduEqualTo(String value) {
            addCriterion("yubaoweidu =", value, "yubaoweidu");
            return (Criteria) this;
        }

        public Criteria andYubaoweiduNotEqualTo(String value) {
            addCriterion("yubaoweidu <>", value, "yubaoweidu");
            return (Criteria) this;
        }

        public Criteria andYubaoweiduGreaterThan(String value) {
            addCriterion("yubaoweidu >", value, "yubaoweidu");
            return (Criteria) this;
        }

        public Criteria andYubaoweiduGreaterThanOrEqualTo(String value) {
            addCriterion("yubaoweidu >=", value, "yubaoweidu");
            return (Criteria) this;
        }

        public Criteria andYubaoweiduLessThan(String value) {
            addCriterion("yubaoweidu <", value, "yubaoweidu");
            return (Criteria) this;
        }

        public Criteria andYubaoweiduLessThanOrEqualTo(String value) {
            addCriterion("yubaoweidu <=", value, "yubaoweidu");
            return (Criteria) this;
        }

        public Criteria andYubaoweiduLike(String value) {
            addCriterion("yubaoweidu like", value, "yubaoweidu");
            return (Criteria) this;
        }

        public Criteria andYubaoweiduNotLike(String value) {
            addCriterion("yubaoweidu not like", value, "yubaoweidu");
            return (Criteria) this;
        }

        public Criteria andYubaoweiduIn(List<String> values) {
            addCriterion("yubaoweidu in", values, "yubaoweidu");
            return (Criteria) this;
        }

        public Criteria andYubaoweiduNotIn(List<String> values) {
            addCriterion("yubaoweidu not in", values, "yubaoweidu");
            return (Criteria) this;
        }

        public Criteria andYubaoweiduBetween(String value1, String value2) {
            addCriterion("yubaoweidu between", value1, value2, "yubaoweidu");
            return (Criteria) this;
        }

        public Criteria andYubaoweiduNotBetween(String value1, String value2) {
            addCriterion("yubaoweidu not between", value1, value2, "yubaoweidu");
            return (Criteria) this;
        }

        public Criteria andYubaofengliIsNull() {
            addCriterion("yubaofengli is null");
            return (Criteria) this;
        }

        public Criteria andYubaofengliIsNotNull() {
            addCriterion("yubaofengli is not null");
            return (Criteria) this;
        }

        public Criteria andYubaofengliEqualTo(String value) {
            addCriterion("yubaofengli =", value, "yubaofengli");
            return (Criteria) this;
        }

        public Criteria andYubaofengliNotEqualTo(String value) {
            addCriterion("yubaofengli <>", value, "yubaofengli");
            return (Criteria) this;
        }

        public Criteria andYubaofengliGreaterThan(String value) {
            addCriterion("yubaofengli >", value, "yubaofengli");
            return (Criteria) this;
        }

        public Criteria andYubaofengliGreaterThanOrEqualTo(String value) {
            addCriterion("yubaofengli >=", value, "yubaofengli");
            return (Criteria) this;
        }

        public Criteria andYubaofengliLessThan(String value) {
            addCriterion("yubaofengli <", value, "yubaofengli");
            return (Criteria) this;
        }

        public Criteria andYubaofengliLessThanOrEqualTo(String value) {
            addCriterion("yubaofengli <=", value, "yubaofengli");
            return (Criteria) this;
        }

        public Criteria andYubaofengliLike(String value) {
            addCriterion("yubaofengli like", value, "yubaofengli");
            return (Criteria) this;
        }

        public Criteria andYubaofengliNotLike(String value) {
            addCriterion("yubaofengli not like", value, "yubaofengli");
            return (Criteria) this;
        }

        public Criteria andYubaofengliIn(List<String> values) {
            addCriterion("yubaofengli in", values, "yubaofengli");
            return (Criteria) this;
        }

        public Criteria andYubaofengliNotIn(List<String> values) {
            addCriterion("yubaofengli not in", values, "yubaofengli");
            return (Criteria) this;
        }

        public Criteria andYubaofengliBetween(String value1, String value2) {
            addCriterion("yubaofengli between", value1, value2, "yubaofengli");
            return (Criteria) this;
        }

        public Criteria andYubaofengliNotBetween(String value1, String value2) {
            addCriterion("yubaofengli not between", value1, value2, "yubaofengli");
            return (Criteria) this;
        }

        public Criteria andYubaoqiyaIsNull() {
            addCriterion("yubaoqiya is null");
            return (Criteria) this;
        }

        public Criteria andYubaoqiyaIsNotNull() {
            addCriterion("yubaoqiya is not null");
            return (Criteria) this;
        }

        public Criteria andYubaoqiyaEqualTo(String value) {
            addCriterion("yubaoqiya =", value, "yubaoqiya");
            return (Criteria) this;
        }

        public Criteria andYubaoqiyaNotEqualTo(String value) {
            addCriterion("yubaoqiya <>", value, "yubaoqiya");
            return (Criteria) this;
        }

        public Criteria andYubaoqiyaGreaterThan(String value) {
            addCriterion("yubaoqiya >", value, "yubaoqiya");
            return (Criteria) this;
        }

        public Criteria andYubaoqiyaGreaterThanOrEqualTo(String value) {
            addCriterion("yubaoqiya >=", value, "yubaoqiya");
            return (Criteria) this;
        }

        public Criteria andYubaoqiyaLessThan(String value) {
            addCriterion("yubaoqiya <", value, "yubaoqiya");
            return (Criteria) this;
        }

        public Criteria andYubaoqiyaLessThanOrEqualTo(String value) {
            addCriterion("yubaoqiya <=", value, "yubaoqiya");
            return (Criteria) this;
        }

        public Criteria andYubaoqiyaLike(String value) {
            addCriterion("yubaoqiya like", value, "yubaoqiya");
            return (Criteria) this;
        }

        public Criteria andYubaoqiyaNotLike(String value) {
            addCriterion("yubaoqiya not like", value, "yubaoqiya");
            return (Criteria) this;
        }

        public Criteria andYubaoqiyaIn(List<String> values) {
            addCriterion("yubaoqiya in", values, "yubaoqiya");
            return (Criteria) this;
        }

        public Criteria andYubaoqiyaNotIn(List<String> values) {
            addCriterion("yubaoqiya not in", values, "yubaoqiya");
            return (Criteria) this;
        }

        public Criteria andYubaoqiyaBetween(String value1, String value2) {
            addCriterion("yubaoqiya between", value1, value2, "yubaoqiya");
            return (Criteria) this;
        }

        public Criteria andYubaoqiyaNotBetween(String value1, String value2) {
            addCriterion("yubaoqiya not between", value1, value2, "yubaoqiya");
            return (Criteria) this;
        }

        public Criteria andUflagIsNull() {
            addCriterion("UFLAG is null");
            return (Criteria) this;
        }

        public Criteria andUflagIsNotNull() {
            addCriterion("UFLAG is not null");
            return (Criteria) this;
        }

        public Criteria andUflagEqualTo(Integer value) {
            addCriterion("UFLAG =", value, "uflag");
            return (Criteria) this;
        }

        public Criteria andUflagNotEqualTo(Integer value) {
            addCriterion("UFLAG <>", value, "uflag");
            return (Criteria) this;
        }

        public Criteria andUflagGreaterThan(Integer value) {
            addCriterion("UFLAG >", value, "uflag");
            return (Criteria) this;
        }

        public Criteria andUflagGreaterThanOrEqualTo(Integer value) {
            addCriterion("UFLAG >=", value, "uflag");
            return (Criteria) this;
        }

        public Criteria andUflagLessThan(Integer value) {
            addCriterion("UFLAG <", value, "uflag");
            return (Criteria) this;
        }

        public Criteria andUflagLessThanOrEqualTo(Integer value) {
            addCriterion("UFLAG <=", value, "uflag");
            return (Criteria) this;
        }

        public Criteria andUflagIn(List<Integer> values) {
            addCriterion("UFLAG in", values, "uflag");
            return (Criteria) this;
        }

        public Criteria andUflagNotIn(List<Integer> values) {
            addCriterion("UFLAG not in", values, "uflag");
            return (Criteria) this;
        }

        public Criteria andUflagBetween(Integer value1, Integer value2) {
            addCriterion("UFLAG between", value1, value2, "uflag");
            return (Criteria) this;
        }

        public Criteria andUflagNotBetween(Integer value1, Integer value2) {
            addCriterion("UFLAG not between", value1, value2, "uflag");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}