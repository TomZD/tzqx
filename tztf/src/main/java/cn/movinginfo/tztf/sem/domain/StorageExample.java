package cn.movinginfo.tztf.sem.domain;

import java.util.ArrayList;
import java.util.List;

public class StorageExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public StorageExample() {
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

        public Criteria andLevelIsNull() {
            addCriterion("level is null");
            return (Criteria) this;
        }

        public Criteria andLevelIsNotNull() {
            addCriterion("level is not null");
            return (Criteria) this;
        }

        public Criteria andLevelEqualTo(String value) {
            addCriterion("level =", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotEqualTo(String value) {
            addCriterion("level <>", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThan(String value) {
            addCriterion("level >", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThanOrEqualTo(String value) {
            addCriterion("level >=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThan(String value) {
            addCriterion("level <", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThanOrEqualTo(String value) {
            addCriterion("level <=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLike(String value) {
            addCriterion("level like", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotLike(String value) {
            addCriterion("level not like", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelIn(List<String> values) {
            addCriterion("level in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotIn(List<String> values) {
            addCriterion("level not in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelBetween(String value1, String value2) {
            addCriterion("level between", value1, value2, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotBetween(String value1, String value2) {
            addCriterion("level not between", value1, value2, "level");
            return (Criteria) this;
        }

        public Criteria andRankIsNull() {
            addCriterion("rank is null");
            return (Criteria) this;
        }

        public Criteria andRankIsNotNull() {
            addCriterion("rank is not null");
            return (Criteria) this;
        }

        public Criteria andRankEqualTo(String value) {
            addCriterion("rank =", value, "rank");
            return (Criteria) this;
        }

        public Criteria andRankNotEqualTo(String value) {
            addCriterion("rank <>", value, "rank");
            return (Criteria) this;
        }

        public Criteria andRankGreaterThan(String value) {
            addCriterion("rank >", value, "rank");
            return (Criteria) this;
        }

        public Criteria andRankGreaterThanOrEqualTo(String value) {
            addCriterion("rank >=", value, "rank");
            return (Criteria) this;
        }

        public Criteria andRankLessThan(String value) {
            addCriterion("rank <", value, "rank");
            return (Criteria) this;
        }

        public Criteria andRankLessThanOrEqualTo(String value) {
            addCriterion("rank <=", value, "rank");
            return (Criteria) this;
        }

        public Criteria andRankLike(String value) {
            addCriterion("rank like", value, "rank");
            return (Criteria) this;
        }

        public Criteria andRankNotLike(String value) {
            addCriterion("rank not like", value, "rank");
            return (Criteria) this;
        }

        public Criteria andRankIn(List<String> values) {
            addCriterion("rank in", values, "rank");
            return (Criteria) this;
        }

        public Criteria andRankNotIn(List<String> values) {
            addCriterion("rank not in", values, "rank");
            return (Criteria) this;
        }

        public Criteria andRankBetween(String value1, String value2) {
            addCriterion("rank between", value1, value2, "rank");
            return (Criteria) this;
        }

        public Criteria andRankNotBetween(String value1, String value2) {
            addCriterion("rank not between", value1, value2, "rank");
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

        public Criteria andPersonNameIsNull() {
            addCriterion("person_name is null");
            return (Criteria) this;
        }

        public Criteria andPersonNameIsNotNull() {
            addCriterion("person_name is not null");
            return (Criteria) this;
        }

        public Criteria andPersonNameEqualTo(String value) {
            addCriterion("person_name =", value, "personName");
            return (Criteria) this;
        }

        public Criteria andPersonNameNotEqualTo(String value) {
            addCriterion("person_name <>", value, "personName");
            return (Criteria) this;
        }

        public Criteria andPersonNameGreaterThan(String value) {
            addCriterion("person_name >", value, "personName");
            return (Criteria) this;
        }

        public Criteria andPersonNameGreaterThanOrEqualTo(String value) {
            addCriterion("person_name >=", value, "personName");
            return (Criteria) this;
        }

        public Criteria andPersonNameLessThan(String value) {
            addCriterion("person_name <", value, "personName");
            return (Criteria) this;
        }

        public Criteria andPersonNameLessThanOrEqualTo(String value) {
            addCriterion("person_name <=", value, "personName");
            return (Criteria) this;
        }

        public Criteria andPersonNameLike(String value) {
            addCriterion("person_name like", value, "personName");
            return (Criteria) this;
        }

        public Criteria andPersonNameNotLike(String value) {
            addCriterion("person_name not like", value, "personName");
            return (Criteria) this;
        }

        public Criteria andPersonNameIn(List<String> values) {
            addCriterion("person_name in", values, "personName");
            return (Criteria) this;
        }

        public Criteria andPersonNameNotIn(List<String> values) {
            addCriterion("person_name not in", values, "personName");
            return (Criteria) this;
        }

        public Criteria andPersonNameBetween(String value1, String value2) {
            addCriterion("person_name between", value1, value2, "personName");
            return (Criteria) this;
        }

        public Criteria andPersonNameNotBetween(String value1, String value2) {
            addCriterion("person_name not between", value1, value2, "personName");
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

        public Criteria andTrafficConditionIsNull() {
            addCriterion("traffic_condition is null");
            return (Criteria) this;
        }

        public Criteria andTrafficConditionIsNotNull() {
            addCriterion("traffic_condition is not null");
            return (Criteria) this;
        }

        public Criteria andTrafficConditionEqualTo(String value) {
            addCriterion("traffic_condition =", value, "trafficCondition");
            return (Criteria) this;
        }

        public Criteria andTrafficConditionNotEqualTo(String value) {
            addCriterion("traffic_condition <>", value, "trafficCondition");
            return (Criteria) this;
        }

        public Criteria andTrafficConditionGreaterThan(String value) {
            addCriterion("traffic_condition >", value, "trafficCondition");
            return (Criteria) this;
        }

        public Criteria andTrafficConditionGreaterThanOrEqualTo(String value) {
            addCriterion("traffic_condition >=", value, "trafficCondition");
            return (Criteria) this;
        }

        public Criteria andTrafficConditionLessThan(String value) {
            addCriterion("traffic_condition <", value, "trafficCondition");
            return (Criteria) this;
        }

        public Criteria andTrafficConditionLessThanOrEqualTo(String value) {
            addCriterion("traffic_condition <=", value, "trafficCondition");
            return (Criteria) this;
        }

        public Criteria andTrafficConditionLike(String value) {
            addCriterion("traffic_condition like", value, "trafficCondition");
            return (Criteria) this;
        }

        public Criteria andTrafficConditionNotLike(String value) {
            addCriterion("traffic_condition not like", value, "trafficCondition");
            return (Criteria) this;
        }

        public Criteria andTrafficConditionIn(List<String> values) {
            addCriterion("traffic_condition in", values, "trafficCondition");
            return (Criteria) this;
        }

        public Criteria andTrafficConditionNotIn(List<String> values) {
            addCriterion("traffic_condition not in", values, "trafficCondition");
            return (Criteria) this;
        }

        public Criteria andTrafficConditionBetween(String value1, String value2) {
            addCriterion("traffic_condition between", value1, value2, "trafficCondition");
            return (Criteria) this;
        }

        public Criteria andTrafficConditionNotBetween(String value1, String value2) {
            addCriterion("traffic_condition not between", value1, value2, "trafficCondition");
            return (Criteria) this;
        }

        public Criteria andUseTimeIsNull() {
            addCriterion("use_time is null");
            return (Criteria) this;
        }

        public Criteria andUseTimeIsNotNull() {
            addCriterion("use_time is not null");
            return (Criteria) this;
        }

        public Criteria andUseTimeEqualTo(String value) {
            addCriterion("use_time =", value, "useTime");
            return (Criteria) this;
        }

        public Criteria andUseTimeNotEqualTo(String value) {
            addCriterion("use_time <>", value, "useTime");
            return (Criteria) this;
        }

        public Criteria andUseTimeGreaterThan(String value) {
            addCriterion("use_time >", value, "useTime");
            return (Criteria) this;
        }

        public Criteria andUseTimeGreaterThanOrEqualTo(String value) {
            addCriterion("use_time >=", value, "useTime");
            return (Criteria) this;
        }

        public Criteria andUseTimeLessThan(String value) {
            addCriterion("use_time <", value, "useTime");
            return (Criteria) this;
        }

        public Criteria andUseTimeLessThanOrEqualTo(String value) {
            addCriterion("use_time <=", value, "useTime");
            return (Criteria) this;
        }

        public Criteria andUseTimeLike(String value) {
            addCriterion("use_time like", value, "useTime");
            return (Criteria) this;
        }

        public Criteria andUseTimeNotLike(String value) {
            addCriterion("use_time not like", value, "useTime");
            return (Criteria) this;
        }

        public Criteria andUseTimeIn(List<String> values) {
            addCriterion("use_time in", values, "useTime");
            return (Criteria) this;
        }

        public Criteria andUseTimeNotIn(List<String> values) {
            addCriterion("use_time not in", values, "useTime");
            return (Criteria) this;
        }

        public Criteria andUseTimeBetween(String value1, String value2) {
            addCriterion("use_time between", value1, value2, "useTime");
            return (Criteria) this;
        }

        public Criteria andUseTimeNotBetween(String value1, String value2) {
            addCriterion("use_time not between", value1, value2, "useTime");
            return (Criteria) this;
        }

        public Criteria andDisasterTypeIsNull() {
            addCriterion("disaster_type is null");
            return (Criteria) this;
        }

        public Criteria andDisasterTypeIsNotNull() {
            addCriterion("disaster_type is not null");
            return (Criteria) this;
        }

        public Criteria andDisasterTypeEqualTo(String value) {
            addCriterion("disaster_type =", value, "disasterType");
            return (Criteria) this;
        }

        public Criteria andDisasterTypeNotEqualTo(String value) {
            addCriterion("disaster_type <>", value, "disasterType");
            return (Criteria) this;
        }

        public Criteria andDisasterTypeGreaterThan(String value) {
            addCriterion("disaster_type >", value, "disasterType");
            return (Criteria) this;
        }

        public Criteria andDisasterTypeGreaterThanOrEqualTo(String value) {
            addCriterion("disaster_type >=", value, "disasterType");
            return (Criteria) this;
        }

        public Criteria andDisasterTypeLessThan(String value) {
            addCriterion("disaster_type <", value, "disasterType");
            return (Criteria) this;
        }

        public Criteria andDisasterTypeLessThanOrEqualTo(String value) {
            addCriterion("disaster_type <=", value, "disasterType");
            return (Criteria) this;
        }

        public Criteria andDisasterTypeLike(String value) {
            addCriterion("disaster_type like", value, "disasterType");
            return (Criteria) this;
        }

        public Criteria andDisasterTypeNotLike(String value) {
            addCriterion("disaster_type not like", value, "disasterType");
            return (Criteria) this;
        }

        public Criteria andDisasterTypeIn(List<String> values) {
            addCriterion("disaster_type in", values, "disasterType");
            return (Criteria) this;
        }

        public Criteria andDisasterTypeNotIn(List<String> values) {
            addCriterion("disaster_type not in", values, "disasterType");
            return (Criteria) this;
        }

        public Criteria andDisasterTypeBetween(String value1, String value2) {
            addCriterion("disaster_type between", value1, value2, "disasterType");
            return (Criteria) this;
        }

        public Criteria andDisasterTypeNotBetween(String value1, String value2) {
            addCriterion("disaster_type not between", value1, value2, "disasterType");
            return (Criteria) this;
        }

        public Criteria andEmergencyTypeIsNull() {
            addCriterion("emergency_type is null");
            return (Criteria) this;
        }

        public Criteria andEmergencyTypeIsNotNull() {
            addCriterion("emergency_type is not null");
            return (Criteria) this;
        }

        public Criteria andEmergencyTypeEqualTo(String value) {
            addCriterion("emergency_type =", value, "emergencyType");
            return (Criteria) this;
        }

        public Criteria andEmergencyTypeNotEqualTo(String value) {
            addCriterion("emergency_type <>", value, "emergencyType");
            return (Criteria) this;
        }

        public Criteria andEmergencyTypeGreaterThan(String value) {
            addCriterion("emergency_type >", value, "emergencyType");
            return (Criteria) this;
        }

        public Criteria andEmergencyTypeGreaterThanOrEqualTo(String value) {
            addCriterion("emergency_type >=", value, "emergencyType");
            return (Criteria) this;
        }

        public Criteria andEmergencyTypeLessThan(String value) {
            addCriterion("emergency_type <", value, "emergencyType");
            return (Criteria) this;
        }

        public Criteria andEmergencyTypeLessThanOrEqualTo(String value) {
            addCriterion("emergency_type <=", value, "emergencyType");
            return (Criteria) this;
        }

        public Criteria andEmergencyTypeLike(String value) {
            addCriterion("emergency_type like", value, "emergencyType");
            return (Criteria) this;
        }

        public Criteria andEmergencyTypeNotLike(String value) {
            addCriterion("emergency_type not like", value, "emergencyType");
            return (Criteria) this;
        }

        public Criteria andEmergencyTypeIn(List<String> values) {
            addCriterion("emergency_type in", values, "emergencyType");
            return (Criteria) this;
        }

        public Criteria andEmergencyTypeNotIn(List<String> values) {
            addCriterion("emergency_type not in", values, "emergencyType");
            return (Criteria) this;
        }

        public Criteria andEmergencyTypeBetween(String value1, String value2) {
            addCriterion("emergency_type between", value1, value2, "emergencyType");
            return (Criteria) this;
        }

        public Criteria andEmergencyTypeNotBetween(String value1, String value2) {
            addCriterion("emergency_type not between", value1, value2, "emergencyType");
            return (Criteria) this;
        }

        public Criteria andProtectAreaIsNull() {
            addCriterion("protect_area is null");
            return (Criteria) this;
        }

        public Criteria andProtectAreaIsNotNull() {
            addCriterion("protect_area is not null");
            return (Criteria) this;
        }

        public Criteria andProtectAreaEqualTo(String value) {
            addCriterion("protect_area =", value, "protectArea");
            return (Criteria) this;
        }

        public Criteria andProtectAreaNotEqualTo(String value) {
            addCriterion("protect_area <>", value, "protectArea");
            return (Criteria) this;
        }

        public Criteria andProtectAreaGreaterThan(String value) {
            addCriterion("protect_area >", value, "protectArea");
            return (Criteria) this;
        }

        public Criteria andProtectAreaGreaterThanOrEqualTo(String value) {
            addCriterion("protect_area >=", value, "protectArea");
            return (Criteria) this;
        }

        public Criteria andProtectAreaLessThan(String value) {
            addCriterion("protect_area <", value, "protectArea");
            return (Criteria) this;
        }

        public Criteria andProtectAreaLessThanOrEqualTo(String value) {
            addCriterion("protect_area <=", value, "protectArea");
            return (Criteria) this;
        }

        public Criteria andProtectAreaLike(String value) {
            addCriterion("protect_area like", value, "protectArea");
            return (Criteria) this;
        }

        public Criteria andProtectAreaNotLike(String value) {
            addCriterion("protect_area not like", value, "protectArea");
            return (Criteria) this;
        }

        public Criteria andProtectAreaIn(List<String> values) {
            addCriterion("protect_area in", values, "protectArea");
            return (Criteria) this;
        }

        public Criteria andProtectAreaNotIn(List<String> values) {
            addCriterion("protect_area not in", values, "protectArea");
            return (Criteria) this;
        }

        public Criteria andProtectAreaBetween(String value1, String value2) {
            addCriterion("protect_area between", value1, value2, "protectArea");
            return (Criteria) this;
        }

        public Criteria andProtectAreaNotBetween(String value1, String value2) {
            addCriterion("protect_area not between", value1, value2, "protectArea");
            return (Criteria) this;
        }

        public Criteria andAccommodateManIsNull() {
            addCriterion("accommodate_man is null");
            return (Criteria) this;
        }

        public Criteria andAccommodateManIsNotNull() {
            addCriterion("accommodate_man is not null");
            return (Criteria) this;
        }

        public Criteria andAccommodateManEqualTo(Integer value) {
            addCriterion("accommodate_man =", value, "accommodateMan");
            return (Criteria) this;
        }

        public Criteria andAccommodateManNotEqualTo(Integer value) {
            addCriterion("accommodate_man <>", value, "accommodateMan");
            return (Criteria) this;
        }

        public Criteria andAccommodateManGreaterThan(Integer value) {
            addCriterion("accommodate_man >", value, "accommodateMan");
            return (Criteria) this;
        }

        public Criteria andAccommodateManGreaterThanOrEqualTo(Integer value) {
            addCriterion("accommodate_man >=", value, "accommodateMan");
            return (Criteria) this;
        }

        public Criteria andAccommodateManLessThan(Integer value) {
            addCriterion("accommodate_man <", value, "accommodateMan");
            return (Criteria) this;
        }

        public Criteria andAccommodateManLessThanOrEqualTo(Integer value) {
            addCriterion("accommodate_man <=", value, "accommodateMan");
            return (Criteria) this;
        }

        public Criteria andAccommodateManIn(List<Integer> values) {
            addCriterion("accommodate_man in", values, "accommodateMan");
            return (Criteria) this;
        }

        public Criteria andAccommodateManNotIn(List<Integer> values) {
            addCriterion("accommodate_man not in", values, "accommodateMan");
            return (Criteria) this;
        }

        public Criteria andAccommodateManBetween(Integer value1, Integer value2) {
            addCriterion("accommodate_man between", value1, value2, "accommodateMan");
            return (Criteria) this;
        }

        public Criteria andAccommodateManNotBetween(Integer value1, Integer value2) {
            addCriterion("accommodate_man not between", value1, value2, "accommodateMan");
            return (Criteria) this;
        }

        public Criteria andMonitorTypeIsNull() {
            addCriterion("monitor_type is null");
            return (Criteria) this;
        }

        public Criteria andMonitorTypeIsNotNull() {
            addCriterion("monitor_type is not null");
            return (Criteria) this;
        }

        public Criteria andMonitorTypeEqualTo(String value) {
            addCriterion("monitor_type =", value, "monitorType");
            return (Criteria) this;
        }

        public Criteria andMonitorTypeNotEqualTo(String value) {
            addCriterion("monitor_type <>", value, "monitorType");
            return (Criteria) this;
        }

        public Criteria andMonitorTypeGreaterThan(String value) {
            addCriterion("monitor_type >", value, "monitorType");
            return (Criteria) this;
        }

        public Criteria andMonitorTypeGreaterThanOrEqualTo(String value) {
            addCriterion("monitor_type >=", value, "monitorType");
            return (Criteria) this;
        }

        public Criteria andMonitorTypeLessThan(String value) {
            addCriterion("monitor_type <", value, "monitorType");
            return (Criteria) this;
        }

        public Criteria andMonitorTypeLessThanOrEqualTo(String value) {
            addCriterion("monitor_type <=", value, "monitorType");
            return (Criteria) this;
        }

        public Criteria andMonitorTypeLike(String value) {
            addCriterion("monitor_type like", value, "monitorType");
            return (Criteria) this;
        }

        public Criteria andMonitorTypeNotLike(String value) {
            addCriterion("monitor_type not like", value, "monitorType");
            return (Criteria) this;
        }

        public Criteria andMonitorTypeIn(List<String> values) {
            addCriterion("monitor_type in", values, "monitorType");
            return (Criteria) this;
        }

        public Criteria andMonitorTypeNotIn(List<String> values) {
            addCriterion("monitor_type not in", values, "monitorType");
            return (Criteria) this;
        }

        public Criteria andMonitorTypeBetween(String value1, String value2) {
            addCriterion("monitor_type between", value1, value2, "monitorType");
            return (Criteria) this;
        }

        public Criteria andMonitorTypeNotBetween(String value1, String value2) {
            addCriterion("monitor_type not between", value1, value2, "monitorType");
            return (Criteria) this;
        }

        public Criteria andStorageCapacityIsNull() {
            addCriterion("storage_capacity is null");
            return (Criteria) this;
        }

        public Criteria andStorageCapacityIsNotNull() {
            addCriterion("storage_capacity is not null");
            return (Criteria) this;
        }

        public Criteria andStorageCapacityEqualTo(String value) {
            addCriterion("storage_capacity =", value, "storageCapacity");
            return (Criteria) this;
        }

        public Criteria andStorageCapacityNotEqualTo(String value) {
            addCriterion("storage_capacity <>", value, "storageCapacity");
            return (Criteria) this;
        }

        public Criteria andStorageCapacityGreaterThan(String value) {
            addCriterion("storage_capacity >", value, "storageCapacity");
            return (Criteria) this;
        }

        public Criteria andStorageCapacityGreaterThanOrEqualTo(String value) {
            addCriterion("storage_capacity >=", value, "storageCapacity");
            return (Criteria) this;
        }

        public Criteria andStorageCapacityLessThan(String value) {
            addCriterion("storage_capacity <", value, "storageCapacity");
            return (Criteria) this;
        }

        public Criteria andStorageCapacityLessThanOrEqualTo(String value) {
            addCriterion("storage_capacity <=", value, "storageCapacity");
            return (Criteria) this;
        }

        public Criteria andStorageCapacityLike(String value) {
            addCriterion("storage_capacity like", value, "storageCapacity");
            return (Criteria) this;
        }

        public Criteria andStorageCapacityNotLike(String value) {
            addCriterion("storage_capacity not like", value, "storageCapacity");
            return (Criteria) this;
        }

        public Criteria andStorageCapacityIn(List<String> values) {
            addCriterion("storage_capacity in", values, "storageCapacity");
            return (Criteria) this;
        }

        public Criteria andStorageCapacityNotIn(List<String> values) {
            addCriterion("storage_capacity not in", values, "storageCapacity");
            return (Criteria) this;
        }

        public Criteria andStorageCapacityBetween(String value1, String value2) {
            addCriterion("storage_capacity between", value1, value2, "storageCapacity");
            return (Criteria) this;
        }

        public Criteria andStorageCapacityNotBetween(String value1, String value2) {
            addCriterion("storage_capacity not between", value1, value2, "storageCapacity");
            return (Criteria) this;
        }

        public Criteria andSquareIsNull() {
            addCriterion("square is null");
            return (Criteria) this;
        }

        public Criteria andSquareIsNotNull() {
            addCriterion("square is not null");
            return (Criteria) this;
        }

        public Criteria andSquareEqualTo(String value) {
            addCriterion("square =", value, "square");
            return (Criteria) this;
        }

        public Criteria andSquareNotEqualTo(String value) {
            addCriterion("square <>", value, "square");
            return (Criteria) this;
        }

        public Criteria andSquareGreaterThan(String value) {
            addCriterion("square >", value, "square");
            return (Criteria) this;
        }

        public Criteria andSquareGreaterThanOrEqualTo(String value) {
            addCriterion("square >=", value, "square");
            return (Criteria) this;
        }

        public Criteria andSquareLessThan(String value) {
            addCriterion("square <", value, "square");
            return (Criteria) this;
        }

        public Criteria andSquareLessThanOrEqualTo(String value) {
            addCriterion("square <=", value, "square");
            return (Criteria) this;
        }

        public Criteria andSquareLike(String value) {
            addCriterion("square like", value, "square");
            return (Criteria) this;
        }

        public Criteria andSquareNotLike(String value) {
            addCriterion("square not like", value, "square");
            return (Criteria) this;
        }

        public Criteria andSquareIn(List<String> values) {
            addCriterion("square in", values, "square");
            return (Criteria) this;
        }

        public Criteria andSquareNotIn(List<String> values) {
            addCriterion("square not in", values, "square");
            return (Criteria) this;
        }

        public Criteria andSquareBetween(String value1, String value2) {
            addCriterion("square between", value1, value2, "square");
            return (Criteria) this;
        }

        public Criteria andSquareNotBetween(String value1, String value2) {
            addCriterion("square not between", value1, value2, "square");
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