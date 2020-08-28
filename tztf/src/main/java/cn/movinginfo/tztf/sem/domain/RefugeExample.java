package cn.movinginfo.tztf.sem.domain;

import java.util.ArrayList;
import java.util.List;

public class RefugeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RefugeExample() {
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

        public Criteria andAccommodateManIsNull() {
            addCriterion("accommodate_man is null");
            return (Criteria) this;
        }

        public Criteria andAccommodateManIsNotNull() {
            addCriterion("accommodate_man is not null");
            return (Criteria) this;
        }

        public Criteria andAccommodateManEqualTo(String value) {
            addCriterion("accommodate_man =", value, "accommodateMan");
            return (Criteria) this;
        }

        public Criteria andAccommodateManNotEqualTo(String value) {
            addCriterion("accommodate_man <>", value, "accommodateMan");
            return (Criteria) this;
        }

        public Criteria andAccommodateManGreaterThan(String value) {
            addCriterion("accommodate_man >", value, "accommodateMan");
            return (Criteria) this;
        }

        public Criteria andAccommodateManGreaterThanOrEqualTo(String value) {
            addCriterion("accommodate_man >=", value, "accommodateMan");
            return (Criteria) this;
        }

        public Criteria andAccommodateManLessThan(String value) {
            addCriterion("accommodate_man <", value, "accommodateMan");
            return (Criteria) this;
        }

        public Criteria andAccommodateManLessThanOrEqualTo(String value) {
            addCriterion("accommodate_man <=", value, "accommodateMan");
            return (Criteria) this;
        }

        public Criteria andAccommodateManLike(String value) {
            addCriterion("accommodate_man like", value, "accommodateMan");
            return (Criteria) this;
        }

        public Criteria andAccommodateManNotLike(String value) {
            addCriterion("accommodate_man not like", value, "accommodateMan");
            return (Criteria) this;
        }

        public Criteria andAccommodateManIn(List<String> values) {
            addCriterion("accommodate_man in", values, "accommodateMan");
            return (Criteria) this;
        }

        public Criteria andAccommodateManNotIn(List<String> values) {
            addCriterion("accommodate_man not in", values, "accommodateMan");
            return (Criteria) this;
        }

        public Criteria andAccommodateManBetween(String value1, String value2) {
            addCriterion("accommodate_man between", value1, value2, "accommodateMan");
            return (Criteria) this;
        }

        public Criteria andAccommodateManNotBetween(String value1, String value2) {
            addCriterion("accommodate_man not between", value1, value2, "accommodateMan");
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

        public Criteria andUserTimeIsNull() {
            addCriterion("user_time is null");
            return (Criteria) this;
        }

        public Criteria andUserTimeIsNotNull() {
            addCriterion("user_time is not null");
            return (Criteria) this;
        }

        public Criteria andUserTimeEqualTo(String value) {
            addCriterion("user_time =", value, "userTime");
            return (Criteria) this;
        }

        public Criteria andUserTimeNotEqualTo(String value) {
            addCriterion("user_time <>", value, "userTime");
            return (Criteria) this;
        }

        public Criteria andUserTimeGreaterThan(String value) {
            addCriterion("user_time >", value, "userTime");
            return (Criteria) this;
        }

        public Criteria andUserTimeGreaterThanOrEqualTo(String value) {
            addCriterion("user_time >=", value, "userTime");
            return (Criteria) this;
        }

        public Criteria andUserTimeLessThan(String value) {
            addCriterion("user_time <", value, "userTime");
            return (Criteria) this;
        }

        public Criteria andUserTimeLessThanOrEqualTo(String value) {
            addCriterion("user_time <=", value, "userTime");
            return (Criteria) this;
        }

        public Criteria andUserTimeLike(String value) {
            addCriterion("user_time like", value, "userTime");
            return (Criteria) this;
        }

        public Criteria andUserTimeNotLike(String value) {
            addCriterion("user_time not like", value, "userTime");
            return (Criteria) this;
        }

        public Criteria andUserTimeIn(List<String> values) {
            addCriterion("user_time in", values, "userTime");
            return (Criteria) this;
        }

        public Criteria andUserTimeNotIn(List<String> values) {
            addCriterion("user_time not in", values, "userTime");
            return (Criteria) this;
        }

        public Criteria andUserTimeBetween(String value1, String value2) {
            addCriterion("user_time between", value1, value2, "userTime");
            return (Criteria) this;
        }

        public Criteria andUserTimeNotBetween(String value1, String value2) {
            addCriterion("user_time not between", value1, value2, "userTime");
            return (Criteria) this;
        }

        public Criteria andUserYearIsNull() {
            addCriterion("user_year is null");
            return (Criteria) this;
        }

        public Criteria andUserYearIsNotNull() {
            addCriterion("user_year is not null");
            return (Criteria) this;
        }

        public Criteria andUserYearEqualTo(String value) {
            addCriterion("user_year =", value, "userYear");
            return (Criteria) this;
        }

        public Criteria andUserYearNotEqualTo(String value) {
            addCriterion("user_year <>", value, "userYear");
            return (Criteria) this;
        }

        public Criteria andUserYearGreaterThan(String value) {
            addCriterion("user_year >", value, "userYear");
            return (Criteria) this;
        }

        public Criteria andUserYearGreaterThanOrEqualTo(String value) {
            addCriterion("user_year >=", value, "userYear");
            return (Criteria) this;
        }

        public Criteria andUserYearLessThan(String value) {
            addCriterion("user_year <", value, "userYear");
            return (Criteria) this;
        }

        public Criteria andUserYearLessThanOrEqualTo(String value) {
            addCriterion("user_year <=", value, "userYear");
            return (Criteria) this;
        }

        public Criteria andUserYearLike(String value) {
            addCriterion("user_year like", value, "userYear");
            return (Criteria) this;
        }

        public Criteria andUserYearNotLike(String value) {
            addCriterion("user_year not like", value, "userYear");
            return (Criteria) this;
        }

        public Criteria andUserYearIn(List<String> values) {
            addCriterion("user_year in", values, "userYear");
            return (Criteria) this;
        }

        public Criteria andUserYearNotIn(List<String> values) {
            addCriterion("user_year not in", values, "userYear");
            return (Criteria) this;
        }

        public Criteria andUserYearBetween(String value1, String value2) {
            addCriterion("user_year between", value1, value2, "userYear");
            return (Criteria) this;
        }

        public Criteria andUserYearNotBetween(String value1, String value2) {
            addCriterion("user_year not between", value1, value2, "userYear");
            return (Criteria) this;
        }

        public Criteria andAseismicStrengthIsNull() {
            addCriterion("aseismic_strength is null");
            return (Criteria) this;
        }

        public Criteria andAseismicStrengthIsNotNull() {
            addCriterion("aseismic_strength is not null");
            return (Criteria) this;
        }

        public Criteria andAseismicStrengthEqualTo(String value) {
            addCriterion("aseismic_strength =", value, "aseismicStrength");
            return (Criteria) this;
        }

        public Criteria andAseismicStrengthNotEqualTo(String value) {
            addCriterion("aseismic_strength <>", value, "aseismicStrength");
            return (Criteria) this;
        }

        public Criteria andAseismicStrengthGreaterThan(String value) {
            addCriterion("aseismic_strength >", value, "aseismicStrength");
            return (Criteria) this;
        }

        public Criteria andAseismicStrengthGreaterThanOrEqualTo(String value) {
            addCriterion("aseismic_strength >=", value, "aseismicStrength");
            return (Criteria) this;
        }

        public Criteria andAseismicStrengthLessThan(String value) {
            addCriterion("aseismic_strength <", value, "aseismicStrength");
            return (Criteria) this;
        }

        public Criteria andAseismicStrengthLessThanOrEqualTo(String value) {
            addCriterion("aseismic_strength <=", value, "aseismicStrength");
            return (Criteria) this;
        }

        public Criteria andAseismicStrengthLike(String value) {
            addCriterion("aseismic_strength like", value, "aseismicStrength");
            return (Criteria) this;
        }

        public Criteria andAseismicStrengthNotLike(String value) {
            addCriterion("aseismic_strength not like", value, "aseismicStrength");
            return (Criteria) this;
        }

        public Criteria andAseismicStrengthIn(List<String> values) {
            addCriterion("aseismic_strength in", values, "aseismicStrength");
            return (Criteria) this;
        }

        public Criteria andAseismicStrengthNotIn(List<String> values) {
            addCriterion("aseismic_strength not in", values, "aseismicStrength");
            return (Criteria) this;
        }

        public Criteria andAseismicStrengthBetween(String value1, String value2) {
            addCriterion("aseismic_strength between", value1, value2, "aseismicStrength");
            return (Criteria) this;
        }

        public Criteria andAseismicStrengthNotBetween(String value1, String value2) {
            addCriterion("aseismic_strength not between", value1, value2, "aseismicStrength");
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

        public Criteria andBasicMessageIsNull() {
            addCriterion("basic_message is null");
            return (Criteria) this;
        }

        public Criteria andBasicMessageIsNotNull() {
            addCriterion("basic_message is not null");
            return (Criteria) this;
        }

        public Criteria andBasicMessageEqualTo(String value) {
            addCriterion("basic_message =", value, "basicMessage");
            return (Criteria) this;
        }

        public Criteria andBasicMessageNotEqualTo(String value) {
            addCriterion("basic_message <>", value, "basicMessage");
            return (Criteria) this;
        }

        public Criteria andBasicMessageGreaterThan(String value) {
            addCriterion("basic_message >", value, "basicMessage");
            return (Criteria) this;
        }

        public Criteria andBasicMessageGreaterThanOrEqualTo(String value) {
            addCriterion("basic_message >=", value, "basicMessage");
            return (Criteria) this;
        }

        public Criteria andBasicMessageLessThan(String value) {
            addCriterion("basic_message <", value, "basicMessage");
            return (Criteria) this;
        }

        public Criteria andBasicMessageLessThanOrEqualTo(String value) {
            addCriterion("basic_message <=", value, "basicMessage");
            return (Criteria) this;
        }

        public Criteria andBasicMessageLike(String value) {
            addCriterion("basic_message like", value, "basicMessage");
            return (Criteria) this;
        }

        public Criteria andBasicMessageNotLike(String value) {
            addCriterion("basic_message not like", value, "basicMessage");
            return (Criteria) this;
        }

        public Criteria andBasicMessageIn(List<String> values) {
            addCriterion("basic_message in", values, "basicMessage");
            return (Criteria) this;
        }

        public Criteria andBasicMessageNotIn(List<String> values) {
            addCriterion("basic_message not in", values, "basicMessage");
            return (Criteria) this;
        }

        public Criteria andBasicMessageBetween(String value1, String value2) {
            addCriterion("basic_message between", value1, value2, "basicMessage");
            return (Criteria) this;
        }

        public Criteria andBasicMessageNotBetween(String value1, String value2) {
            addCriterion("basic_message not between", value1, value2, "basicMessage");
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