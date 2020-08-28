package cn.movinginfo.tztf.sem.domain;

import java.util.ArrayList;
import java.util.List;

public class MedicalExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MedicalExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("type like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("type not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andLongitudeIsNull() {
            addCriterion("longitude is null");
            return (Criteria) this;
        }

        public Criteria andLongitudeIsNotNull() {
            addCriterion("longitude is not null");
            return (Criteria) this;
        }

        public Criteria andLongitudeEqualTo(String value) {
            addCriterion("longitude =", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotEqualTo(String value) {
            addCriterion("longitude <>", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeGreaterThan(String value) {
            addCriterion("longitude >", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeGreaterThanOrEqualTo(String value) {
            addCriterion("longitude >=", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeLessThan(String value) {
            addCriterion("longitude <", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeLessThanOrEqualTo(String value) {
            addCriterion("longitude <=", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeLike(String value) {
            addCriterion("longitude like", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotLike(String value) {
            addCriterion("longitude not like", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeIn(List<String> values) {
            addCriterion("longitude in", values, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotIn(List<String> values) {
            addCriterion("longitude not in", values, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeBetween(String value1, String value2) {
            addCriterion("longitude between", value1, value2, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotBetween(String value1, String value2) {
            addCriterion("longitude not between", value1, value2, "longitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeIsNull() {
            addCriterion("latitude is null");
            return (Criteria) this;
        }

        public Criteria andLatitudeIsNotNull() {
            addCriterion("latitude is not null");
            return (Criteria) this;
        }

        public Criteria andLatitudeEqualTo(String value) {
            addCriterion("latitude =", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotEqualTo(String value) {
            addCriterion("latitude <>", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeGreaterThan(String value) {
            addCriterion("latitude >", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeGreaterThanOrEqualTo(String value) {
            addCriterion("latitude >=", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeLessThan(String value) {
            addCriterion("latitude <", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeLessThanOrEqualTo(String value) {
            addCriterion("latitude <=", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeLike(String value) {
            addCriterion("latitude like", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotLike(String value) {
            addCriterion("latitude not like", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeIn(List<String> values) {
            addCriterion("latitude in", values, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotIn(List<String> values) {
            addCriterion("latitude not in", values, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeBetween(String value1, String value2) {
            addCriterion("latitude between", value1, value2, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotBetween(String value1, String value2) {
            addCriterion("latitude not between", value1, value2, "latitude");
            return (Criteria) this;
        }

        public Criteria andAreaIsNull() {
            addCriterion("area is null");
            return (Criteria) this;
        }

        public Criteria andAreaIsNotNull() {
            addCriterion("area is not null");
            return (Criteria) this;
        }

        public Criteria andAreaEqualTo(String value) {
            addCriterion("area =", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotEqualTo(String value) {
            addCriterion("area <>", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaGreaterThan(String value) {
            addCriterion("area >", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaGreaterThanOrEqualTo(String value) {
            addCriterion("area >=", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaLessThan(String value) {
            addCriterion("area <", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaLessThanOrEqualTo(String value) {
            addCriterion("area <=", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaLike(String value) {
            addCriterion("area like", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotLike(String value) {
            addCriterion("area not like", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaIn(List<String> values) {
            addCriterion("area in", values, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotIn(List<String> values) {
            addCriterion("area not in", values, "area");
            return (Criteria) this;
        }

        public Criteria andAreaBetween(String value1, String value2) {
            addCriterion("area between", value1, value2, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotBetween(String value1, String value2) {
            addCriterion("area not between", value1, value2, "area");
            return (Criteria) this;
        }

        public Criteria andCompanyIsNull() {
            addCriterion("company is null");
            return (Criteria) this;
        }

        public Criteria andCompanyIsNotNull() {
            addCriterion("company is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyEqualTo(String value) {
            addCriterion("company =", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyNotEqualTo(String value) {
            addCriterion("company <>", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyGreaterThan(String value) {
            addCriterion("company >", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyGreaterThanOrEqualTo(String value) {
            addCriterion("company >=", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyLessThan(String value) {
            addCriterion("company <", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyLessThanOrEqualTo(String value) {
            addCriterion("company <=", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyLike(String value) {
            addCriterion("company like", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyNotLike(String value) {
            addCriterion("company not like", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyIn(List<String> values) {
            addCriterion("company in", values, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyNotIn(List<String> values) {
            addCriterion("company not in", values, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyBetween(String value1, String value2) {
            addCriterion("company between", value1, value2, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyNotBetween(String value1, String value2) {
            addCriterion("company not between", value1, value2, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyRankIsNull() {
            addCriterion("company_rank is null");
            return (Criteria) this;
        }

        public Criteria andCompanyRankIsNotNull() {
            addCriterion("company_rank is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyRankEqualTo(String value) {
            addCriterion("company_rank =", value, "companyRank");
            return (Criteria) this;
        }

        public Criteria andCompanyRankNotEqualTo(String value) {
            addCriterion("company_rank <>", value, "companyRank");
            return (Criteria) this;
        }

        public Criteria andCompanyRankGreaterThan(String value) {
            addCriterion("company_rank >", value, "companyRank");
            return (Criteria) this;
        }

        public Criteria andCompanyRankGreaterThanOrEqualTo(String value) {
            addCriterion("company_rank >=", value, "companyRank");
            return (Criteria) this;
        }

        public Criteria andCompanyRankLessThan(String value) {
            addCriterion("company_rank <", value, "companyRank");
            return (Criteria) this;
        }

        public Criteria andCompanyRankLessThanOrEqualTo(String value) {
            addCriterion("company_rank <=", value, "companyRank");
            return (Criteria) this;
        }

        public Criteria andCompanyRankLike(String value) {
            addCriterion("company_rank like", value, "companyRank");
            return (Criteria) this;
        }

        public Criteria andCompanyRankNotLike(String value) {
            addCriterion("company_rank not like", value, "companyRank");
            return (Criteria) this;
        }

        public Criteria andCompanyRankIn(List<String> values) {
            addCriterion("company_rank in", values, "companyRank");
            return (Criteria) this;
        }

        public Criteria andCompanyRankNotIn(List<String> values) {
            addCriterion("company_rank not in", values, "companyRank");
            return (Criteria) this;
        }

        public Criteria andCompanyRankBetween(String value1, String value2) {
            addCriterion("company_rank between", value1, value2, "companyRank");
            return (Criteria) this;
        }

        public Criteria andCompanyRankNotBetween(String value1, String value2) {
            addCriterion("company_rank not between", value1, value2, "companyRank");
            return (Criteria) this;
        }

        public Criteria andSecretIsNull() {
            addCriterion("secret is null");
            return (Criteria) this;
        }

        public Criteria andSecretIsNotNull() {
            addCriterion("secret is not null");
            return (Criteria) this;
        }

        public Criteria andSecretEqualTo(String value) {
            addCriterion("secret =", value, "secret");
            return (Criteria) this;
        }

        public Criteria andSecretNotEqualTo(String value) {
            addCriterion("secret <>", value, "secret");
            return (Criteria) this;
        }

        public Criteria andSecretGreaterThan(String value) {
            addCriterion("secret >", value, "secret");
            return (Criteria) this;
        }

        public Criteria andSecretGreaterThanOrEqualTo(String value) {
            addCriterion("secret >=", value, "secret");
            return (Criteria) this;
        }

        public Criteria andSecretLessThan(String value) {
            addCriterion("secret <", value, "secret");
            return (Criteria) this;
        }

        public Criteria andSecretLessThanOrEqualTo(String value) {
            addCriterion("secret <=", value, "secret");
            return (Criteria) this;
        }

        public Criteria andSecretLike(String value) {
            addCriterion("secret like", value, "secret");
            return (Criteria) this;
        }

        public Criteria andSecretNotLike(String value) {
            addCriterion("secret not like", value, "secret");
            return (Criteria) this;
        }

        public Criteria andSecretIn(List<String> values) {
            addCriterion("secret in", values, "secret");
            return (Criteria) this;
        }

        public Criteria andSecretNotIn(List<String> values) {
            addCriterion("secret not in", values, "secret");
            return (Criteria) this;
        }

        public Criteria andSecretBetween(String value1, String value2) {
            addCriterion("secret between", value1, value2, "secret");
            return (Criteria) this;
        }

        public Criteria andSecretNotBetween(String value1, String value2) {
            addCriterion("secret not between", value1, value2, "secret");
            return (Criteria) this;
        }

        public Criteria andChargeManIsNull() {
            addCriterion("charge_man is null");
            return (Criteria) this;
        }

        public Criteria andChargeManIsNotNull() {
            addCriterion("charge_man is not null");
            return (Criteria) this;
        }

        public Criteria andChargeManEqualTo(String value) {
            addCriterion("charge_man =", value, "chargeMan");
            return (Criteria) this;
        }

        public Criteria andChargeManNotEqualTo(String value) {
            addCriterion("charge_man <>", value, "chargeMan");
            return (Criteria) this;
        }

        public Criteria andChargeManGreaterThan(String value) {
            addCriterion("charge_man >", value, "chargeMan");
            return (Criteria) this;
        }

        public Criteria andChargeManGreaterThanOrEqualTo(String value) {
            addCriterion("charge_man >=", value, "chargeMan");
            return (Criteria) this;
        }

        public Criteria andChargeManLessThan(String value) {
            addCriterion("charge_man <", value, "chargeMan");
            return (Criteria) this;
        }

        public Criteria andChargeManLessThanOrEqualTo(String value) {
            addCriterion("charge_man <=", value, "chargeMan");
            return (Criteria) this;
        }

        public Criteria andChargeManLike(String value) {
            addCriterion("charge_man like", value, "chargeMan");
            return (Criteria) this;
        }

        public Criteria andChargeManNotLike(String value) {
            addCriterion("charge_man not like", value, "chargeMan");
            return (Criteria) this;
        }

        public Criteria andChargeManIn(List<String> values) {
            addCriterion("charge_man in", values, "chargeMan");
            return (Criteria) this;
        }

        public Criteria andChargeManNotIn(List<String> values) {
            addCriterion("charge_man not in", values, "chargeMan");
            return (Criteria) this;
        }

        public Criteria andChargeManBetween(String value1, String value2) {
            addCriterion("charge_man between", value1, value2, "chargeMan");
            return (Criteria) this;
        }

        public Criteria andChargeManNotBetween(String value1, String value2) {
            addCriterion("charge_man not between", value1, value2, "chargeMan");
            return (Criteria) this;
        }

        public Criteria andOfficeTelIsNull() {
            addCriterion("office_tel is null");
            return (Criteria) this;
        }

        public Criteria andOfficeTelIsNotNull() {
            addCriterion("office_tel is not null");
            return (Criteria) this;
        }

        public Criteria andOfficeTelEqualTo(String value) {
            addCriterion("office_tel =", value, "officeTel");
            return (Criteria) this;
        }

        public Criteria andOfficeTelNotEqualTo(String value) {
            addCriterion("office_tel <>", value, "officeTel");
            return (Criteria) this;
        }

        public Criteria andOfficeTelGreaterThan(String value) {
            addCriterion("office_tel >", value, "officeTel");
            return (Criteria) this;
        }

        public Criteria andOfficeTelGreaterThanOrEqualTo(String value) {
            addCriterion("office_tel >=", value, "officeTel");
            return (Criteria) this;
        }

        public Criteria andOfficeTelLessThan(String value) {
            addCriterion("office_tel <", value, "officeTel");
            return (Criteria) this;
        }

        public Criteria andOfficeTelLessThanOrEqualTo(String value) {
            addCriterion("office_tel <=", value, "officeTel");
            return (Criteria) this;
        }

        public Criteria andOfficeTelLike(String value) {
            addCriterion("office_tel like", value, "officeTel");
            return (Criteria) this;
        }

        public Criteria andOfficeTelNotLike(String value) {
            addCriterion("office_tel not like", value, "officeTel");
            return (Criteria) this;
        }

        public Criteria andOfficeTelIn(List<String> values) {
            addCriterion("office_tel in", values, "officeTel");
            return (Criteria) this;
        }

        public Criteria andOfficeTelNotIn(List<String> values) {
            addCriterion("office_tel not in", values, "officeTel");
            return (Criteria) this;
        }

        public Criteria andOfficeTelBetween(String value1, String value2) {
            addCriterion("office_tel between", value1, value2, "officeTel");
            return (Criteria) this;
        }

        public Criteria andOfficeTelNotBetween(String value1, String value2) {
            addCriterion("office_tel not between", value1, value2, "officeTel");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNull() {
            addCriterion("phone is null");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNotNull() {
            addCriterion("phone is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneEqualTo(String value) {
            addCriterion("phone =", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotEqualTo(String value) {
            addCriterion("phone <>", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThan(String value) {
            addCriterion("phone >", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("phone >=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThan(String value) {
            addCriterion("phone <", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThanOrEqualTo(String value) {
            addCriterion("phone <=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLike(String value) {
            addCriterion("phone like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotLike(String value) {
            addCriterion("phone not like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneIn(List<String> values) {
            addCriterion("phone in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotIn(List<String> values) {
            addCriterion("phone not in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneBetween(String value1, String value2) {
            addCriterion("phone between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotBetween(String value1, String value2) {
            addCriterion("phone not between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andHomeTelIsNull() {
            addCriterion("home_tel is null");
            return (Criteria) this;
        }

        public Criteria andHomeTelIsNotNull() {
            addCriterion("home_tel is not null");
            return (Criteria) this;
        }

        public Criteria andHomeTelEqualTo(String value) {
            addCriterion("home_tel =", value, "homeTel");
            return (Criteria) this;
        }

        public Criteria andHomeTelNotEqualTo(String value) {
            addCriterion("home_tel <>", value, "homeTel");
            return (Criteria) this;
        }

        public Criteria andHomeTelGreaterThan(String value) {
            addCriterion("home_tel >", value, "homeTel");
            return (Criteria) this;
        }

        public Criteria andHomeTelGreaterThanOrEqualTo(String value) {
            addCriterion("home_tel >=", value, "homeTel");
            return (Criteria) this;
        }

        public Criteria andHomeTelLessThan(String value) {
            addCriterion("home_tel <", value, "homeTel");
            return (Criteria) this;
        }

        public Criteria andHomeTelLessThanOrEqualTo(String value) {
            addCriterion("home_tel <=", value, "homeTel");
            return (Criteria) this;
        }

        public Criteria andHomeTelLike(String value) {
            addCriterion("home_tel like", value, "homeTel");
            return (Criteria) this;
        }

        public Criteria andHomeTelNotLike(String value) {
            addCriterion("home_tel not like", value, "homeTel");
            return (Criteria) this;
        }

        public Criteria andHomeTelIn(List<String> values) {
            addCriterion("home_tel in", values, "homeTel");
            return (Criteria) this;
        }

        public Criteria andHomeTelNotIn(List<String> values) {
            addCriterion("home_tel not in", values, "homeTel");
            return (Criteria) this;
        }

        public Criteria andHomeTelBetween(String value1, String value2) {
            addCriterion("home_tel between", value1, value2, "homeTel");
            return (Criteria) this;
        }

        public Criteria andHomeTelNotBetween(String value1, String value2) {
            addCriterion("home_tel not between", value1, value2, "homeTel");
            return (Criteria) this;
        }

        public Criteria andFaxIsNull() {
            addCriterion("fax is null");
            return (Criteria) this;
        }

        public Criteria andFaxIsNotNull() {
            addCriterion("fax is not null");
            return (Criteria) this;
        }

        public Criteria andFaxEqualTo(String value) {
            addCriterion("fax =", value, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxNotEqualTo(String value) {
            addCriterion("fax <>", value, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxGreaterThan(String value) {
            addCriterion("fax >", value, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxGreaterThanOrEqualTo(String value) {
            addCriterion("fax >=", value, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxLessThan(String value) {
            addCriterion("fax <", value, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxLessThanOrEqualTo(String value) {
            addCriterion("fax <=", value, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxLike(String value) {
            addCriterion("fax like", value, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxNotLike(String value) {
            addCriterion("fax not like", value, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxIn(List<String> values) {
            addCriterion("fax in", values, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxNotIn(List<String> values) {
            addCriterion("fax not in", values, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxBetween(String value1, String value2) {
            addCriterion("fax between", value1, value2, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxNotBetween(String value1, String value2) {
            addCriterion("fax not between", value1, value2, "fax");
            return (Criteria) this;
        }

        public Criteria andMailIsNull() {
            addCriterion("mail is null");
            return (Criteria) this;
        }

        public Criteria andMailIsNotNull() {
            addCriterion("mail is not null");
            return (Criteria) this;
        }

        public Criteria andMailEqualTo(String value) {
            addCriterion("mail =", value, "mail");
            return (Criteria) this;
        }

        public Criteria andMailNotEqualTo(String value) {
            addCriterion("mail <>", value, "mail");
            return (Criteria) this;
        }

        public Criteria andMailGreaterThan(String value) {
            addCriterion("mail >", value, "mail");
            return (Criteria) this;
        }

        public Criteria andMailGreaterThanOrEqualTo(String value) {
            addCriterion("mail >=", value, "mail");
            return (Criteria) this;
        }

        public Criteria andMailLessThan(String value) {
            addCriterion("mail <", value, "mail");
            return (Criteria) this;
        }

        public Criteria andMailLessThanOrEqualTo(String value) {
            addCriterion("mail <=", value, "mail");
            return (Criteria) this;
        }

        public Criteria andMailLike(String value) {
            addCriterion("mail like", value, "mail");
            return (Criteria) this;
        }

        public Criteria andMailNotLike(String value) {
            addCriterion("mail not like", value, "mail");
            return (Criteria) this;
        }

        public Criteria andMailIn(List<String> values) {
            addCriterion("mail in", values, "mail");
            return (Criteria) this;
        }

        public Criteria andMailNotIn(List<String> values) {
            addCriterion("mail not in", values, "mail");
            return (Criteria) this;
        }

        public Criteria andMailBetween(String value1, String value2) {
            addCriterion("mail between", value1, value2, "mail");
            return (Criteria) this;
        }

        public Criteria andMailNotBetween(String value1, String value2) {
            addCriterion("mail not between", value1, value2, "mail");
            return (Criteria) this;
        }

        public Criteria andLinkmanIsNull() {
            addCriterion("linkman is null");
            return (Criteria) this;
        }

        public Criteria andLinkmanIsNotNull() {
            addCriterion("linkman is not null");
            return (Criteria) this;
        }

        public Criteria andLinkmanEqualTo(String value) {
            addCriterion("linkman =", value, "linkman");
            return (Criteria) this;
        }

        public Criteria andLinkmanNotEqualTo(String value) {
            addCriterion("linkman <>", value, "linkman");
            return (Criteria) this;
        }

        public Criteria andLinkmanGreaterThan(String value) {
            addCriterion("linkman >", value, "linkman");
            return (Criteria) this;
        }

        public Criteria andLinkmanGreaterThanOrEqualTo(String value) {
            addCriterion("linkman >=", value, "linkman");
            return (Criteria) this;
        }

        public Criteria andLinkmanLessThan(String value) {
            addCriterion("linkman <", value, "linkman");
            return (Criteria) this;
        }

        public Criteria andLinkmanLessThanOrEqualTo(String value) {
            addCriterion("linkman <=", value, "linkman");
            return (Criteria) this;
        }

        public Criteria andLinkmanLike(String value) {
            addCriterion("linkman like", value, "linkman");
            return (Criteria) this;
        }

        public Criteria andLinkmanNotLike(String value) {
            addCriterion("linkman not like", value, "linkman");
            return (Criteria) this;
        }

        public Criteria andLinkmanIn(List<String> values) {
            addCriterion("linkman in", values, "linkman");
            return (Criteria) this;
        }

        public Criteria andLinkmanNotIn(List<String> values) {
            addCriterion("linkman not in", values, "linkman");
            return (Criteria) this;
        }

        public Criteria andLinkmanBetween(String value1, String value2) {
            addCriterion("linkman between", value1, value2, "linkman");
            return (Criteria) this;
        }

        public Criteria andLinkmanNotBetween(String value1, String value2) {
            addCriterion("linkman not between", value1, value2, "linkman");
            return (Criteria) this;
        }

        public Criteria andLinkmanOfficeTelIsNull() {
            addCriterion("linkman_office_tel is null");
            return (Criteria) this;
        }

        public Criteria andLinkmanOfficeTelIsNotNull() {
            addCriterion("linkman_office_tel is not null");
            return (Criteria) this;
        }

        public Criteria andLinkmanOfficeTelEqualTo(String value) {
            addCriterion("linkman_office_tel =", value, "linkmanOfficeTel");
            return (Criteria) this;
        }

        public Criteria andLinkmanOfficeTelNotEqualTo(String value) {
            addCriterion("linkman_office_tel <>", value, "linkmanOfficeTel");
            return (Criteria) this;
        }

        public Criteria andLinkmanOfficeTelGreaterThan(String value) {
            addCriterion("linkman_office_tel >", value, "linkmanOfficeTel");
            return (Criteria) this;
        }

        public Criteria andLinkmanOfficeTelGreaterThanOrEqualTo(String value) {
            addCriterion("linkman_office_tel >=", value, "linkmanOfficeTel");
            return (Criteria) this;
        }

        public Criteria andLinkmanOfficeTelLessThan(String value) {
            addCriterion("linkman_office_tel <", value, "linkmanOfficeTel");
            return (Criteria) this;
        }

        public Criteria andLinkmanOfficeTelLessThanOrEqualTo(String value) {
            addCriterion("linkman_office_tel <=", value, "linkmanOfficeTel");
            return (Criteria) this;
        }

        public Criteria andLinkmanOfficeTelLike(String value) {
            addCriterion("linkman_office_tel like", value, "linkmanOfficeTel");
            return (Criteria) this;
        }

        public Criteria andLinkmanOfficeTelNotLike(String value) {
            addCriterion("linkman_office_tel not like", value, "linkmanOfficeTel");
            return (Criteria) this;
        }

        public Criteria andLinkmanOfficeTelIn(List<String> values) {
            addCriterion("linkman_office_tel in", values, "linkmanOfficeTel");
            return (Criteria) this;
        }

        public Criteria andLinkmanOfficeTelNotIn(List<String> values) {
            addCriterion("linkman_office_tel not in", values, "linkmanOfficeTel");
            return (Criteria) this;
        }

        public Criteria andLinkmanOfficeTelBetween(String value1, String value2) {
            addCriterion("linkman_office_tel between", value1, value2, "linkmanOfficeTel");
            return (Criteria) this;
        }

        public Criteria andLinkmanOfficeTelNotBetween(String value1, String value2) {
            addCriterion("linkman_office_tel not between", value1, value2, "linkmanOfficeTel");
            return (Criteria) this;
        }

        public Criteria andLinkmanPhoneIsNull() {
            addCriterion("linkman_phone is null");
            return (Criteria) this;
        }

        public Criteria andLinkmanPhoneIsNotNull() {
            addCriterion("linkman_phone is not null");
            return (Criteria) this;
        }

        public Criteria andLinkmanPhoneEqualTo(String value) {
            addCriterion("linkman_phone =", value, "linkmanPhone");
            return (Criteria) this;
        }

        public Criteria andLinkmanPhoneNotEqualTo(String value) {
            addCriterion("linkman_phone <>", value, "linkmanPhone");
            return (Criteria) this;
        }

        public Criteria andLinkmanPhoneGreaterThan(String value) {
            addCriterion("linkman_phone >", value, "linkmanPhone");
            return (Criteria) this;
        }

        public Criteria andLinkmanPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("linkman_phone >=", value, "linkmanPhone");
            return (Criteria) this;
        }

        public Criteria andLinkmanPhoneLessThan(String value) {
            addCriterion("linkman_phone <", value, "linkmanPhone");
            return (Criteria) this;
        }

        public Criteria andLinkmanPhoneLessThanOrEqualTo(String value) {
            addCriterion("linkman_phone <=", value, "linkmanPhone");
            return (Criteria) this;
        }

        public Criteria andLinkmanPhoneLike(String value) {
            addCriterion("linkman_phone like", value, "linkmanPhone");
            return (Criteria) this;
        }

        public Criteria andLinkmanPhoneNotLike(String value) {
            addCriterion("linkman_phone not like", value, "linkmanPhone");
            return (Criteria) this;
        }

        public Criteria andLinkmanPhoneIn(List<String> values) {
            addCriterion("linkman_phone in", values, "linkmanPhone");
            return (Criteria) this;
        }

        public Criteria andLinkmanPhoneNotIn(List<String> values) {
            addCriterion("linkman_phone not in", values, "linkmanPhone");
            return (Criteria) this;
        }

        public Criteria andLinkmanPhoneBetween(String value1, String value2) {
            addCriterion("linkman_phone between", value1, value2, "linkmanPhone");
            return (Criteria) this;
        }

        public Criteria andLinkmanPhoneNotBetween(String value1, String value2) {
            addCriterion("linkman_phone not between", value1, value2, "linkmanPhone");
            return (Criteria) this;
        }

        public Criteria andLinkmanHomeTelIsNull() {
            addCriterion("linkman_home_tel is null");
            return (Criteria) this;
        }

        public Criteria andLinkmanHomeTelIsNotNull() {
            addCriterion("linkman_home_tel is not null");
            return (Criteria) this;
        }

        public Criteria andLinkmanHomeTelEqualTo(String value) {
            addCriterion("linkman_home_tel =", value, "linkmanHomeTel");
            return (Criteria) this;
        }

        public Criteria andLinkmanHomeTelNotEqualTo(String value) {
            addCriterion("linkman_home_tel <>", value, "linkmanHomeTel");
            return (Criteria) this;
        }

        public Criteria andLinkmanHomeTelGreaterThan(String value) {
            addCriterion("linkman_home_tel >", value, "linkmanHomeTel");
            return (Criteria) this;
        }

        public Criteria andLinkmanHomeTelGreaterThanOrEqualTo(String value) {
            addCriterion("linkman_home_tel >=", value, "linkmanHomeTel");
            return (Criteria) this;
        }

        public Criteria andLinkmanHomeTelLessThan(String value) {
            addCriterion("linkman_home_tel <", value, "linkmanHomeTel");
            return (Criteria) this;
        }

        public Criteria andLinkmanHomeTelLessThanOrEqualTo(String value) {
            addCriterion("linkman_home_tel <=", value, "linkmanHomeTel");
            return (Criteria) this;
        }

        public Criteria andLinkmanHomeTelLike(String value) {
            addCriterion("linkman_home_tel like", value, "linkmanHomeTel");
            return (Criteria) this;
        }

        public Criteria andLinkmanHomeTelNotLike(String value) {
            addCriterion("linkman_home_tel not like", value, "linkmanHomeTel");
            return (Criteria) this;
        }

        public Criteria andLinkmanHomeTelIn(List<String> values) {
            addCriterion("linkman_home_tel in", values, "linkmanHomeTel");
            return (Criteria) this;
        }

        public Criteria andLinkmanHomeTelNotIn(List<String> values) {
            addCriterion("linkman_home_tel not in", values, "linkmanHomeTel");
            return (Criteria) this;
        }

        public Criteria andLinkmanHomeTelBetween(String value1, String value2) {
            addCriterion("linkman_home_tel between", value1, value2, "linkmanHomeTel");
            return (Criteria) this;
        }

        public Criteria andLinkmanHomeTelNotBetween(String value1, String value2) {
            addCriterion("linkman_home_tel not between", value1, value2, "linkmanHomeTel");
            return (Criteria) this;
        }

        public Criteria andLinkmanFaxIsNull() {
            addCriterion("linkman_fax is null");
            return (Criteria) this;
        }

        public Criteria andLinkmanFaxIsNotNull() {
            addCriterion("linkman_fax is not null");
            return (Criteria) this;
        }

        public Criteria andLinkmanFaxEqualTo(String value) {
            addCriterion("linkman_fax =", value, "linkmanFax");
            return (Criteria) this;
        }

        public Criteria andLinkmanFaxNotEqualTo(String value) {
            addCriterion("linkman_fax <>", value, "linkmanFax");
            return (Criteria) this;
        }

        public Criteria andLinkmanFaxGreaterThan(String value) {
            addCriterion("linkman_fax >", value, "linkmanFax");
            return (Criteria) this;
        }

        public Criteria andLinkmanFaxGreaterThanOrEqualTo(String value) {
            addCriterion("linkman_fax >=", value, "linkmanFax");
            return (Criteria) this;
        }

        public Criteria andLinkmanFaxLessThan(String value) {
            addCriterion("linkman_fax <", value, "linkmanFax");
            return (Criteria) this;
        }

        public Criteria andLinkmanFaxLessThanOrEqualTo(String value) {
            addCriterion("linkman_fax <=", value, "linkmanFax");
            return (Criteria) this;
        }

        public Criteria andLinkmanFaxLike(String value) {
            addCriterion("linkman_fax like", value, "linkmanFax");
            return (Criteria) this;
        }

        public Criteria andLinkmanFaxNotLike(String value) {
            addCriterion("linkman_fax not like", value, "linkmanFax");
            return (Criteria) this;
        }

        public Criteria andLinkmanFaxIn(List<String> values) {
            addCriterion("linkman_fax in", values, "linkmanFax");
            return (Criteria) this;
        }

        public Criteria andLinkmanFaxNotIn(List<String> values) {
            addCriterion("linkman_fax not in", values, "linkmanFax");
            return (Criteria) this;
        }

        public Criteria andLinkmanFaxBetween(String value1, String value2) {
            addCriterion("linkman_fax between", value1, value2, "linkmanFax");
            return (Criteria) this;
        }

        public Criteria andLinkmanFaxNotBetween(String value1, String value2) {
            addCriterion("linkman_fax not between", value1, value2, "linkmanFax");
            return (Criteria) this;
        }

        public Criteria andLinkmanMailIsNull() {
            addCriterion("linkman_mail is null");
            return (Criteria) this;
        }

        public Criteria andLinkmanMailIsNotNull() {
            addCriterion("linkman_mail is not null");
            return (Criteria) this;
        }

        public Criteria andLinkmanMailEqualTo(String value) {
            addCriterion("linkman_mail =", value, "linkmanMail");
            return (Criteria) this;
        }

        public Criteria andLinkmanMailNotEqualTo(String value) {
            addCriterion("linkman_mail <>", value, "linkmanMail");
            return (Criteria) this;
        }

        public Criteria andLinkmanMailGreaterThan(String value) {
            addCriterion("linkman_mail >", value, "linkmanMail");
            return (Criteria) this;
        }

        public Criteria andLinkmanMailGreaterThanOrEqualTo(String value) {
            addCriterion("linkman_mail >=", value, "linkmanMail");
            return (Criteria) this;
        }

        public Criteria andLinkmanMailLessThan(String value) {
            addCriterion("linkman_mail <", value, "linkmanMail");
            return (Criteria) this;
        }

        public Criteria andLinkmanMailLessThanOrEqualTo(String value) {
            addCriterion("linkman_mail <=", value, "linkmanMail");
            return (Criteria) this;
        }

        public Criteria andLinkmanMailLike(String value) {
            addCriterion("linkman_mail like", value, "linkmanMail");
            return (Criteria) this;
        }

        public Criteria andLinkmanMailNotLike(String value) {
            addCriterion("linkman_mail not like", value, "linkmanMail");
            return (Criteria) this;
        }

        public Criteria andLinkmanMailIn(List<String> values) {
            addCriterion("linkman_mail in", values, "linkmanMail");
            return (Criteria) this;
        }

        public Criteria andLinkmanMailNotIn(List<String> values) {
            addCriterion("linkman_mail not in", values, "linkmanMail");
            return (Criteria) this;
        }

        public Criteria andLinkmanMailBetween(String value1, String value2) {
            addCriterion("linkman_mail between", value1, value2, "linkmanMail");
            return (Criteria) this;
        }

        public Criteria andLinkmanMailNotBetween(String value1, String value2) {
            addCriterion("linkman_mail not between", value1, value2, "linkmanMail");
            return (Criteria) this;
        }

        public Criteria andLinkmanEmergencyIsNull() {
            addCriterion("linkman_emergency is null");
            return (Criteria) this;
        }

        public Criteria andLinkmanEmergencyIsNotNull() {
            addCriterion("linkman_emergency is not null");
            return (Criteria) this;
        }

        public Criteria andLinkmanEmergencyEqualTo(String value) {
            addCriterion("linkman_emergency =", value, "linkmanEmergency");
            return (Criteria) this;
        }

        public Criteria andLinkmanEmergencyNotEqualTo(String value) {
            addCriterion("linkman_emergency <>", value, "linkmanEmergency");
            return (Criteria) this;
        }

        public Criteria andLinkmanEmergencyGreaterThan(String value) {
            addCriterion("linkman_emergency >", value, "linkmanEmergency");
            return (Criteria) this;
        }

        public Criteria andLinkmanEmergencyGreaterThanOrEqualTo(String value) {
            addCriterion("linkman_emergency >=", value, "linkmanEmergency");
            return (Criteria) this;
        }

        public Criteria andLinkmanEmergencyLessThan(String value) {
            addCriterion("linkman_emergency <", value, "linkmanEmergency");
            return (Criteria) this;
        }

        public Criteria andLinkmanEmergencyLessThanOrEqualTo(String value) {
            addCriterion("linkman_emergency <=", value, "linkmanEmergency");
            return (Criteria) this;
        }

        public Criteria andLinkmanEmergencyLike(String value) {
            addCriterion("linkman_emergency like", value, "linkmanEmergency");
            return (Criteria) this;
        }

        public Criteria andLinkmanEmergencyNotLike(String value) {
            addCriterion("linkman_emergency not like", value, "linkmanEmergency");
            return (Criteria) this;
        }

        public Criteria andLinkmanEmergencyIn(List<String> values) {
            addCriterion("linkman_emergency in", values, "linkmanEmergency");
            return (Criteria) this;
        }

        public Criteria andLinkmanEmergencyNotIn(List<String> values) {
            addCriterion("linkman_emergency not in", values, "linkmanEmergency");
            return (Criteria) this;
        }

        public Criteria andLinkmanEmergencyBetween(String value1, String value2) {
            addCriterion("linkman_emergency between", value1, value2, "linkmanEmergency");
            return (Criteria) this;
        }

        public Criteria andLinkmanEmergencyNotBetween(String value1, String value2) {
            addCriterion("linkman_emergency not between", value1, value2, "linkmanEmergency");
            return (Criteria) this;
        }

        public Criteria andNumBedIsNull() {
            addCriterion("num_bed is null");
            return (Criteria) this;
        }

        public Criteria andNumBedIsNotNull() {
            addCriterion("num_bed is not null");
            return (Criteria) this;
        }

        public Criteria andNumBedEqualTo(String value) {
            addCriterion("num_bed =", value, "numBed");
            return (Criteria) this;
        }

        public Criteria andNumBedNotEqualTo(String value) {
            addCriterion("num_bed <>", value, "numBed");
            return (Criteria) this;
        }

        public Criteria andNumBedGreaterThan(String value) {
            addCriterion("num_bed >", value, "numBed");
            return (Criteria) this;
        }

        public Criteria andNumBedGreaterThanOrEqualTo(String value) {
            addCriterion("num_bed >=", value, "numBed");
            return (Criteria) this;
        }

        public Criteria andNumBedLessThan(String value) {
            addCriterion("num_bed <", value, "numBed");
            return (Criteria) this;
        }

        public Criteria andNumBedLessThanOrEqualTo(String value) {
            addCriterion("num_bed <=", value, "numBed");
            return (Criteria) this;
        }

        public Criteria andNumBedLike(String value) {
            addCriterion("num_bed like", value, "numBed");
            return (Criteria) this;
        }

        public Criteria andNumBedNotLike(String value) {
            addCriterion("num_bed not like", value, "numBed");
            return (Criteria) this;
        }

        public Criteria andNumBedIn(List<String> values) {
            addCriterion("num_bed in", values, "numBed");
            return (Criteria) this;
        }

        public Criteria andNumBedNotIn(List<String> values) {
            addCriterion("num_bed not in", values, "numBed");
            return (Criteria) this;
        }

        public Criteria andNumBedBetween(String value1, String value2) {
            addCriterion("num_bed between", value1, value2, "numBed");
            return (Criteria) this;
        }

        public Criteria andNumBedNotBetween(String value1, String value2) {
            addCriterion("num_bed not between", value1, value2, "numBed");
            return (Criteria) this;
        }

        public Criteria andNumDoctorIsNull() {
            addCriterion("num_doctor is null");
            return (Criteria) this;
        }

        public Criteria andNumDoctorIsNotNull() {
            addCriterion("num_doctor is not null");
            return (Criteria) this;
        }

        public Criteria andNumDoctorEqualTo(String value) {
            addCriterion("num_doctor =", value, "numDoctor");
            return (Criteria) this;
        }

        public Criteria andNumDoctorNotEqualTo(String value) {
            addCriterion("num_doctor <>", value, "numDoctor");
            return (Criteria) this;
        }

        public Criteria andNumDoctorGreaterThan(String value) {
            addCriterion("num_doctor >", value, "numDoctor");
            return (Criteria) this;
        }

        public Criteria andNumDoctorGreaterThanOrEqualTo(String value) {
            addCriterion("num_doctor >=", value, "numDoctor");
            return (Criteria) this;
        }

        public Criteria andNumDoctorLessThan(String value) {
            addCriterion("num_doctor <", value, "numDoctor");
            return (Criteria) this;
        }

        public Criteria andNumDoctorLessThanOrEqualTo(String value) {
            addCriterion("num_doctor <=", value, "numDoctor");
            return (Criteria) this;
        }

        public Criteria andNumDoctorLike(String value) {
            addCriterion("num_doctor like", value, "numDoctor");
            return (Criteria) this;
        }

        public Criteria andNumDoctorNotLike(String value) {
            addCriterion("num_doctor not like", value, "numDoctor");
            return (Criteria) this;
        }

        public Criteria andNumDoctorIn(List<String> values) {
            addCriterion("num_doctor in", values, "numDoctor");
            return (Criteria) this;
        }

        public Criteria andNumDoctorNotIn(List<String> values) {
            addCriterion("num_doctor not in", values, "numDoctor");
            return (Criteria) this;
        }

        public Criteria andNumDoctorBetween(String value1, String value2) {
            addCriterion("num_doctor between", value1, value2, "numDoctor");
            return (Criteria) this;
        }

        public Criteria andNumDoctorNotBetween(String value1, String value2) {
            addCriterion("num_doctor not between", value1, value2, "numDoctor");
            return (Criteria) this;
        }

        public Criteria andNumNurseIsNull() {
            addCriterion("num_nurse is null");
            return (Criteria) this;
        }

        public Criteria andNumNurseIsNotNull() {
            addCriterion("num_nurse is not null");
            return (Criteria) this;
        }

        public Criteria andNumNurseEqualTo(String value) {
            addCriterion("num_nurse =", value, "numNurse");
            return (Criteria) this;
        }

        public Criteria andNumNurseNotEqualTo(String value) {
            addCriterion("num_nurse <>", value, "numNurse");
            return (Criteria) this;
        }

        public Criteria andNumNurseGreaterThan(String value) {
            addCriterion("num_nurse >", value, "numNurse");
            return (Criteria) this;
        }

        public Criteria andNumNurseGreaterThanOrEqualTo(String value) {
            addCriterion("num_nurse >=", value, "numNurse");
            return (Criteria) this;
        }

        public Criteria andNumNurseLessThan(String value) {
            addCriterion("num_nurse <", value, "numNurse");
            return (Criteria) this;
        }

        public Criteria andNumNurseLessThanOrEqualTo(String value) {
            addCriterion("num_nurse <=", value, "numNurse");
            return (Criteria) this;
        }

        public Criteria andNumNurseLike(String value) {
            addCriterion("num_nurse like", value, "numNurse");
            return (Criteria) this;
        }

        public Criteria andNumNurseNotLike(String value) {
            addCriterion("num_nurse not like", value, "numNurse");
            return (Criteria) this;
        }

        public Criteria andNumNurseIn(List<String> values) {
            addCriterion("num_nurse in", values, "numNurse");
            return (Criteria) this;
        }

        public Criteria andNumNurseNotIn(List<String> values) {
            addCriterion("num_nurse not in", values, "numNurse");
            return (Criteria) this;
        }

        public Criteria andNumNurseBetween(String value1, String value2) {
            addCriterion("num_nurse between", value1, value2, "numNurse");
            return (Criteria) this;
        }

        public Criteria andNumNurseNotBetween(String value1, String value2) {
            addCriterion("num_nurse not between", value1, value2, "numNurse");
            return (Criteria) this;
        }

        public Criteria andNumAmbulanceIsNull() {
            addCriterion("num_ambulance is null");
            return (Criteria) this;
        }

        public Criteria andNumAmbulanceIsNotNull() {
            addCriterion("num_ambulance is not null");
            return (Criteria) this;
        }

        public Criteria andNumAmbulanceEqualTo(String value) {
            addCriterion("num_ambulance =", value, "numAmbulance");
            return (Criteria) this;
        }

        public Criteria andNumAmbulanceNotEqualTo(String value) {
            addCriterion("num_ambulance <>", value, "numAmbulance");
            return (Criteria) this;
        }

        public Criteria andNumAmbulanceGreaterThan(String value) {
            addCriterion("num_ambulance >", value, "numAmbulance");
            return (Criteria) this;
        }

        public Criteria andNumAmbulanceGreaterThanOrEqualTo(String value) {
            addCriterion("num_ambulance >=", value, "numAmbulance");
            return (Criteria) this;
        }

        public Criteria andNumAmbulanceLessThan(String value) {
            addCriterion("num_ambulance <", value, "numAmbulance");
            return (Criteria) this;
        }

        public Criteria andNumAmbulanceLessThanOrEqualTo(String value) {
            addCriterion("num_ambulance <=", value, "numAmbulance");
            return (Criteria) this;
        }

        public Criteria andNumAmbulanceLike(String value) {
            addCriterion("num_ambulance like", value, "numAmbulance");
            return (Criteria) this;
        }

        public Criteria andNumAmbulanceNotLike(String value) {
            addCriterion("num_ambulance not like", value, "numAmbulance");
            return (Criteria) this;
        }

        public Criteria andNumAmbulanceIn(List<String> values) {
            addCriterion("num_ambulance in", values, "numAmbulance");
            return (Criteria) this;
        }

        public Criteria andNumAmbulanceNotIn(List<String> values) {
            addCriterion("num_ambulance not in", values, "numAmbulance");
            return (Criteria) this;
        }

        public Criteria andNumAmbulanceBetween(String value1, String value2) {
            addCriterion("num_ambulance between", value1, value2, "numAmbulance");
            return (Criteria) this;
        }

        public Criteria andNumAmbulanceNotBetween(String value1, String value2) {
            addCriterion("num_ambulance not between", value1, value2, "numAmbulance");
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