package cn.movinginfo.tztf.sen.model;

import java.util.ArrayList;
import java.util.List;

public class TyphoonDataExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TyphoonDataExample() {
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

        public Criteria andNumIsNull() {
            addCriterion("num is null");
            return (Criteria) this;
        }

        public Criteria andNumIsNotNull() {
            addCriterion("num is not null");
            return (Criteria) this;
        }

        public Criteria andNumEqualTo(String value) {
            addCriterion("num =", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotEqualTo(String value) {
            addCriterion("num <>", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumGreaterThan(String value) {
            addCriterion("num >", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumGreaterThanOrEqualTo(String value) {
            addCriterion("num >=", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumLessThan(String value) {
            addCriterion("num <", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumLessThanOrEqualTo(String value) {
            addCriterion("num <=", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumLike(String value) {
            addCriterion("num like", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotLike(String value) {
            addCriterion("num not like", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumIn(List<String> values) {
            addCriterion("num in", values, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotIn(List<String> values) {
            addCriterion("num not in", values, "num");
            return (Criteria) this;
        }

        public Criteria andNumBetween(String value1, String value2) {
            addCriterion("num between", value1, value2, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotBetween(String value1, String value2) {
            addCriterion("num not between", value1, value2, "num");
            return (Criteria) this;
        }

        public Criteria andNowtimeIsNull() {
            addCriterion("nowtime is null");
            return (Criteria) this;
        }

        public Criteria andNowtimeIsNotNull() {
            addCriterion("nowtime is not null");
            return (Criteria) this;
        }

        public Criteria andNowtimeEqualTo(String value) {
            addCriterion("nowtime =", value, "nowtime");
            return (Criteria) this;
        }

        public Criteria andNowtimeNotEqualTo(String value) {
            addCriterion("nowtime <>", value, "nowtime");
            return (Criteria) this;
        }

        public Criteria andNowtimeGreaterThan(String value) {
            addCriterion("nowtime >", value, "nowtime");
            return (Criteria) this;
        }

        public Criteria andNowtimeGreaterThanOrEqualTo(String value) {
            addCriterion("nowtime >=", value, "nowtime");
            return (Criteria) this;
        }

        public Criteria andNowtimeLessThan(String value) {
            addCriterion("nowtime <", value, "nowtime");
            return (Criteria) this;
        }

        public Criteria andNowtimeLessThanOrEqualTo(String value) {
            addCriterion("nowtime <=", value, "nowtime");
            return (Criteria) this;
        }

        public Criteria andNowtimeLike(String value) {
            addCriterion("nowtime like", value, "nowtime");
            return (Criteria) this;
        }

        public Criteria andNowtimeNotLike(String value) {
            addCriterion("nowtime not like", value, "nowtime");
            return (Criteria) this;
        }

        public Criteria andNowtimeIn(List<String> values) {
            addCriterion("nowtime in", values, "nowtime");
            return (Criteria) this;
        }

        public Criteria andNowtimeNotIn(List<String> values) {
            addCriterion("nowtime not in", values, "nowtime");
            return (Criteria) this;
        }

        public Criteria andNowtimeBetween(String value1, String value2) {
            addCriterion("nowtime between", value1, value2, "nowtime");
            return (Criteria) this;
        }

        public Criteria andNowtimeNotBetween(String value1, String value2) {
            addCriterion("nowtime not between", value1, value2, "nowtime");
            return (Criteria) this;
        }

        public Criteria andLonIsNull() {
            addCriterion("lon is null");
            return (Criteria) this;
        }

        public Criteria andLonIsNotNull() {
            addCriterion("lon is not null");
            return (Criteria) this;
        }

        public Criteria andLonEqualTo(String value) {
            addCriterion("lon =", value, "lon");
            return (Criteria) this;
        }

        public Criteria andLonNotEqualTo(String value) {
            addCriterion("lon <>", value, "lon");
            return (Criteria) this;
        }

        public Criteria andLonGreaterThan(String value) {
            addCriterion("lon >", value, "lon");
            return (Criteria) this;
        }

        public Criteria andLonGreaterThanOrEqualTo(String value) {
            addCriterion("lon >=", value, "lon");
            return (Criteria) this;
        }

        public Criteria andLonLessThan(String value) {
            addCriterion("lon <", value, "lon");
            return (Criteria) this;
        }

        public Criteria andLonLessThanOrEqualTo(String value) {
            addCriterion("lon <=", value, "lon");
            return (Criteria) this;
        }

        public Criteria andLonLike(String value) {
            addCriterion("lon like", value, "lon");
            return (Criteria) this;
        }

        public Criteria andLonNotLike(String value) {
            addCriterion("lon not like", value, "lon");
            return (Criteria) this;
        }

        public Criteria andLonIn(List<String> values) {
            addCriterion("lon in", values, "lon");
            return (Criteria) this;
        }

        public Criteria andLonNotIn(List<String> values) {
            addCriterion("lon not in", values, "lon");
            return (Criteria) this;
        }

        public Criteria andLonBetween(String value1, String value2) {
            addCriterion("lon between", value1, value2, "lon");
            return (Criteria) this;
        }

        public Criteria andLonNotBetween(String value1, String value2) {
            addCriterion("lon not between", value1, value2, "lon");
            return (Criteria) this;
        }

        public Criteria andLatIsNull() {
            addCriterion("lat is null");
            return (Criteria) this;
        }

        public Criteria andLatIsNotNull() {
            addCriterion("lat is not null");
            return (Criteria) this;
        }

        public Criteria andLatEqualTo(String value) {
            addCriterion("lat =", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatNotEqualTo(String value) {
            addCriterion("lat <>", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatGreaterThan(String value) {
            addCriterion("lat >", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatGreaterThanOrEqualTo(String value) {
            addCriterion("lat >=", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatLessThan(String value) {
            addCriterion("lat <", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatLessThanOrEqualTo(String value) {
            addCriterion("lat <=", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatLike(String value) {
            addCriterion("lat like", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatNotLike(String value) {
            addCriterion("lat not like", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatIn(List<String> values) {
            addCriterion("lat in", values, "lat");
            return (Criteria) this;
        }

        public Criteria andLatNotIn(List<String> values) {
            addCriterion("lat not in", values, "lat");
            return (Criteria) this;
        }

        public Criteria andLatBetween(String value1, String value2) {
            addCriterion("lat between", value1, value2, "lat");
            return (Criteria) this;
        }

        public Criteria andLatNotBetween(String value1, String value2) {
            addCriterion("lat not between", value1, value2, "lat");
            return (Criteria) this;
        }

        public Criteria andMoveSpeedIsNull() {
            addCriterion("move_speed is null");
            return (Criteria) this;
        }

        public Criteria andMoveSpeedIsNotNull() {
            addCriterion("move_speed is not null");
            return (Criteria) this;
        }

        public Criteria andMoveSpeedEqualTo(String value) {
            addCriterion("move_speed =", value, "moveSpeed");
            return (Criteria) this;
        }

        public Criteria andMoveSpeedNotEqualTo(String value) {
            addCriterion("move_speed <>", value, "moveSpeed");
            return (Criteria) this;
        }

        public Criteria andMoveSpeedGreaterThan(String value) {
            addCriterion("move_speed >", value, "moveSpeed");
            return (Criteria) this;
        }

        public Criteria andMoveSpeedGreaterThanOrEqualTo(String value) {
            addCriterion("move_speed >=", value, "moveSpeed");
            return (Criteria) this;
        }

        public Criteria andMoveSpeedLessThan(String value) {
            addCriterion("move_speed <", value, "moveSpeed");
            return (Criteria) this;
        }

        public Criteria andMoveSpeedLessThanOrEqualTo(String value) {
            addCriterion("move_speed <=", value, "moveSpeed");
            return (Criteria) this;
        }

        public Criteria andMoveSpeedLike(String value) {
            addCriterion("move_speed like", value, "moveSpeed");
            return (Criteria) this;
        }

        public Criteria andMoveSpeedNotLike(String value) {
            addCriterion("move_speed not like", value, "moveSpeed");
            return (Criteria) this;
        }

        public Criteria andMoveSpeedIn(List<String> values) {
            addCriterion("move_speed in", values, "moveSpeed");
            return (Criteria) this;
        }

        public Criteria andMoveSpeedNotIn(List<String> values) {
            addCriterion("move_speed not in", values, "moveSpeed");
            return (Criteria) this;
        }

        public Criteria andMoveSpeedBetween(String value1, String value2) {
            addCriterion("move_speed between", value1, value2, "moveSpeed");
            return (Criteria) this;
        }

        public Criteria andMoveSpeedNotBetween(String value1, String value2) {
            addCriterion("move_speed not between", value1, value2, "moveSpeed");
            return (Criteria) this;
        }

        public Criteria andMoveDirectionIsNull() {
            addCriterion("move_direction is null");
            return (Criteria) this;
        }

        public Criteria andMoveDirectionIsNotNull() {
            addCriterion("move_direction is not null");
            return (Criteria) this;
        }

        public Criteria andMoveDirectionEqualTo(String value) {
            addCriterion("move_direction =", value, "moveDirection");
            return (Criteria) this;
        }

        public Criteria andMoveDirectionNotEqualTo(String value) {
            addCriterion("move_direction <>", value, "moveDirection");
            return (Criteria) this;
        }

        public Criteria andMoveDirectionGreaterThan(String value) {
            addCriterion("move_direction >", value, "moveDirection");
            return (Criteria) this;
        }

        public Criteria andMoveDirectionGreaterThanOrEqualTo(String value) {
            addCriterion("move_direction >=", value, "moveDirection");
            return (Criteria) this;
        }

        public Criteria andMoveDirectionLessThan(String value) {
            addCriterion("move_direction <", value, "moveDirection");
            return (Criteria) this;
        }

        public Criteria andMoveDirectionLessThanOrEqualTo(String value) {
            addCriterion("move_direction <=", value, "moveDirection");
            return (Criteria) this;
        }

        public Criteria andMoveDirectionLike(String value) {
            addCriterion("move_direction like", value, "moveDirection");
            return (Criteria) this;
        }

        public Criteria andMoveDirectionNotLike(String value) {
            addCriterion("move_direction not like", value, "moveDirection");
            return (Criteria) this;
        }

        public Criteria andMoveDirectionIn(List<String> values) {
            addCriterion("move_direction in", values, "moveDirection");
            return (Criteria) this;
        }

        public Criteria andMoveDirectionNotIn(List<String> values) {
            addCriterion("move_direction not in", values, "moveDirection");
            return (Criteria) this;
        }

        public Criteria andMoveDirectionBetween(String value1, String value2) {
            addCriterion("move_direction between", value1, value2, "moveDirection");
            return (Criteria) this;
        }

        public Criteria andMoveDirectionNotBetween(String value1, String value2) {
            addCriterion("move_direction not between", value1, value2, "moveDirection");
            return (Criteria) this;
        }

        public Criteria andPressureIsNull() {
            addCriterion("pressure is null");
            return (Criteria) this;
        }

        public Criteria andPressureIsNotNull() {
            addCriterion("pressure is not null");
            return (Criteria) this;
        }

        public Criteria andPressureEqualTo(String value) {
            addCriterion("pressure =", value, "pressure");
            return (Criteria) this;
        }

        public Criteria andPressureNotEqualTo(String value) {
            addCriterion("pressure <>", value, "pressure");
            return (Criteria) this;
        }

        public Criteria andPressureGreaterThan(String value) {
            addCriterion("pressure >", value, "pressure");
            return (Criteria) this;
        }

        public Criteria andPressureGreaterThanOrEqualTo(String value) {
            addCriterion("pressure >=", value, "pressure");
            return (Criteria) this;
        }

        public Criteria andPressureLessThan(String value) {
            addCriterion("pressure <", value, "pressure");
            return (Criteria) this;
        }

        public Criteria andPressureLessThanOrEqualTo(String value) {
            addCriterion("pressure <=", value, "pressure");
            return (Criteria) this;
        }

        public Criteria andPressureLike(String value) {
            addCriterion("pressure like", value, "pressure");
            return (Criteria) this;
        }

        public Criteria andPressureNotLike(String value) {
            addCriterion("pressure not like", value, "pressure");
            return (Criteria) this;
        }

        public Criteria andPressureIn(List<String> values) {
            addCriterion("pressure in", values, "pressure");
            return (Criteria) this;
        }

        public Criteria andPressureNotIn(List<String> values) {
            addCriterion("pressure not in", values, "pressure");
            return (Criteria) this;
        }

        public Criteria andPressureBetween(String value1, String value2) {
            addCriterion("pressure between", value1, value2, "pressure");
            return (Criteria) this;
        }

        public Criteria andPressureNotBetween(String value1, String value2) {
            addCriterion("pressure not between", value1, value2, "pressure");
            return (Criteria) this;
        }

        public Criteria andWindLevelIsNull() {
            addCriterion("wind_level is null");
            return (Criteria) this;
        }

        public Criteria andWindLevelIsNotNull() {
            addCriterion("wind_level is not null");
            return (Criteria) this;
        }

        public Criteria andWindLevelEqualTo(String value) {
            addCriterion("wind_level =", value, "windLevel");
            return (Criteria) this;
        }

        public Criteria andWindLevelNotEqualTo(String value) {
            addCriterion("wind_level <>", value, "windLevel");
            return (Criteria) this;
        }

        public Criteria andWindLevelGreaterThan(String value) {
            addCriterion("wind_level >", value, "windLevel");
            return (Criteria) this;
        }

        public Criteria andWindLevelGreaterThanOrEqualTo(String value) {
            addCriterion("wind_level >=", value, "windLevel");
            return (Criteria) this;
        }

        public Criteria andWindLevelLessThan(String value) {
            addCriterion("wind_level <", value, "windLevel");
            return (Criteria) this;
        }

        public Criteria andWindLevelLessThanOrEqualTo(String value) {
            addCriterion("wind_level <=", value, "windLevel");
            return (Criteria) this;
        }

        public Criteria andWindLevelLike(String value) {
            addCriterion("wind_level like", value, "windLevel");
            return (Criteria) this;
        }

        public Criteria andWindLevelNotLike(String value) {
            addCriterion("wind_level not like", value, "windLevel");
            return (Criteria) this;
        }

        public Criteria andWindLevelIn(List<String> values) {
            addCriterion("wind_level in", values, "windLevel");
            return (Criteria) this;
        }

        public Criteria andWindLevelNotIn(List<String> values) {
            addCriterion("wind_level not in", values, "windLevel");
            return (Criteria) this;
        }

        public Criteria andWindLevelBetween(String value1, String value2) {
            addCriterion("wind_level between", value1, value2, "windLevel");
            return (Criteria) this;
        }

        public Criteria andWindLevelNotBetween(String value1, String value2) {
            addCriterion("wind_level not between", value1, value2, "windLevel");
            return (Criteria) this;
        }

        public Criteria andGradeIsNull() {
            addCriterion("grade is null");
            return (Criteria) this;
        }

        public Criteria andGradeIsNotNull() {
            addCriterion("grade is not null");
            return (Criteria) this;
        }

        public Criteria andGradeEqualTo(String value) {
            addCriterion("grade =", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeNotEqualTo(String value) {
            addCriterion("grade <>", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeGreaterThan(String value) {
            addCriterion("grade >", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeGreaterThanOrEqualTo(String value) {
            addCriterion("grade >=", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeLessThan(String value) {
            addCriterion("grade <", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeLessThanOrEqualTo(String value) {
            addCriterion("grade <=", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeLike(String value) {
            addCriterion("grade like", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeNotLike(String value) {
            addCriterion("grade not like", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeIn(List<String> values) {
            addCriterion("grade in", values, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeNotIn(List<String> values) {
            addCriterion("grade not in", values, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeBetween(String value1, String value2) {
            addCriterion("grade between", value1, value2, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeNotBetween(String value1, String value2) {
            addCriterion("grade not between", value1, value2, "grade");
            return (Criteria) this;
        }

        public Criteria andDataSourceIsNull() {
            addCriterion("data_source is null");
            return (Criteria) this;
        }

        public Criteria andDataSourceIsNotNull() {
            addCriterion("data_source is not null");
            return (Criteria) this;
        }

        public Criteria andDataSourceEqualTo(String value) {
            addCriterion("data_source =", value, "dataSource");
            return (Criteria) this;
        }

        public Criteria andDataSourceNotEqualTo(String value) {
            addCriterion("data_source <>", value, "dataSource");
            return (Criteria) this;
        }

        public Criteria andDataSourceGreaterThan(String value) {
            addCriterion("data_source >", value, "dataSource");
            return (Criteria) this;
        }

        public Criteria andDataSourceGreaterThanOrEqualTo(String value) {
            addCriterion("data_source >=", value, "dataSource");
            return (Criteria) this;
        }

        public Criteria andDataSourceLessThan(String value) {
            addCriterion("data_source <", value, "dataSource");
            return (Criteria) this;
        }

        public Criteria andDataSourceLessThanOrEqualTo(String value) {
            addCriterion("data_source <=", value, "dataSource");
            return (Criteria) this;
        }

        public Criteria andDataSourceLike(String value) {
            addCriterion("data_source like", value, "dataSource");
            return (Criteria) this;
        }

        public Criteria andDataSourceNotLike(String value) {
            addCriterion("data_source not like", value, "dataSource");
            return (Criteria) this;
        }

        public Criteria andDataSourceIn(List<String> values) {
            addCriterion("data_source in", values, "dataSource");
            return (Criteria) this;
        }

        public Criteria andDataSourceNotIn(List<String> values) {
            addCriterion("data_source not in", values, "dataSource");
            return (Criteria) this;
        }

        public Criteria andDataSourceBetween(String value1, String value2) {
            addCriterion("data_source between", value1, value2, "dataSource");
            return (Criteria) this;
        }

        public Criteria andDataSourceNotBetween(String value1, String value2) {
            addCriterion("data_source not between", value1, value2, "dataSource");
            return (Criteria) this;
        }

        public Criteria andForeTimeIsNull() {
            addCriterion("fore_time is null");
            return (Criteria) this;
        }

        public Criteria andForeTimeIsNotNull() {
            addCriterion("fore_time is not null");
            return (Criteria) this;
        }

        public Criteria andForeTimeEqualTo(String value) {
            addCriterion("fore_time =", value, "foreTime");
            return (Criteria) this;
        }

        public Criteria andForeTimeNotEqualTo(String value) {
            addCriterion("fore_time <>", value, "foreTime");
            return (Criteria) this;
        }

        public Criteria andForeTimeGreaterThan(String value) {
            addCriterion("fore_time >", value, "foreTime");
            return (Criteria) this;
        }

        public Criteria andForeTimeGreaterThanOrEqualTo(String value) {
            addCriterion("fore_time >=", value, "foreTime");
            return (Criteria) this;
        }

        public Criteria andForeTimeLessThan(String value) {
            addCriterion("fore_time <", value, "foreTime");
            return (Criteria) this;
        }

        public Criteria andForeTimeLessThanOrEqualTo(String value) {
            addCriterion("fore_time <=", value, "foreTime");
            return (Criteria) this;
        }

        public Criteria andForeTimeLike(String value) {
            addCriterion("fore_time like", value, "foreTime");
            return (Criteria) this;
        }

        public Criteria andForeTimeNotLike(String value) {
            addCriterion("fore_time not like", value, "foreTime");
            return (Criteria) this;
        }

        public Criteria andForeTimeIn(List<String> values) {
            addCriterion("fore_time in", values, "foreTime");
            return (Criteria) this;
        }

        public Criteria andForeTimeNotIn(List<String> values) {
            addCriterion("fore_time not in", values, "foreTime");
            return (Criteria) this;
        }

        public Criteria andForeTimeBetween(String value1, String value2) {
            addCriterion("fore_time between", value1, value2, "foreTime");
            return (Criteria) this;
        }

        public Criteria andForeTimeNotBetween(String value1, String value2) {
            addCriterion("fore_time not between", value1, value2, "foreTime");
            return (Criteria) this;
        }

        public Criteria andPublisherIsNull() {
            addCriterion("publisher is null");
            return (Criteria) this;
        }

        public Criteria andPublisherIsNotNull() {
            addCriterion("publisher is not null");
            return (Criteria) this;
        }

        public Criteria andPublisherEqualTo(String value) {
            addCriterion("publisher =", value, "publisher");
            return (Criteria) this;
        }

        public Criteria andPublisherNotEqualTo(String value) {
            addCriterion("publisher <>", value, "publisher");
            return (Criteria) this;
        }

        public Criteria andPublisherGreaterThan(String value) {
            addCriterion("publisher >", value, "publisher");
            return (Criteria) this;
        }

        public Criteria andPublisherGreaterThanOrEqualTo(String value) {
            addCriterion("publisher >=", value, "publisher");
            return (Criteria) this;
        }

        public Criteria andPublisherLessThan(String value) {
            addCriterion("publisher <", value, "publisher");
            return (Criteria) this;
        }

        public Criteria andPublisherLessThanOrEqualTo(String value) {
            addCriterion("publisher <=", value, "publisher");
            return (Criteria) this;
        }

        public Criteria andPublisherLike(String value) {
            addCriterion("publisher like", value, "publisher");
            return (Criteria) this;
        }

        public Criteria andPublisherNotLike(String value) {
            addCriterion("publisher not like", value, "publisher");
            return (Criteria) this;
        }

        public Criteria andPublisherIn(List<String> values) {
            addCriterion("publisher in", values, "publisher");
            return (Criteria) this;
        }

        public Criteria andPublisherNotIn(List<String> values) {
            addCriterion("publisher not in", values, "publisher");
            return (Criteria) this;
        }

        public Criteria andPublisherBetween(String value1, String value2) {
            addCriterion("publisher between", value1, value2, "publisher");
            return (Criteria) this;
        }

        public Criteria andPublisherNotBetween(String value1, String value2) {
            addCriterion("publisher not between", value1, value2, "publisher");
            return (Criteria) this;
        }

        public Criteria andForePresIsNull() {
            addCriterion("fore_pres is null");
            return (Criteria) this;
        }

        public Criteria andForePresIsNotNull() {
            addCriterion("fore_pres is not null");
            return (Criteria) this;
        }

        public Criteria andForePresEqualTo(String value) {
            addCriterion("fore_pres =", value, "forePres");
            return (Criteria) this;
        }

        public Criteria andForePresNotEqualTo(String value) {
            addCriterion("fore_pres <>", value, "forePres");
            return (Criteria) this;
        }

        public Criteria andForePresGreaterThan(String value) {
            addCriterion("fore_pres >", value, "forePres");
            return (Criteria) this;
        }

        public Criteria andForePresGreaterThanOrEqualTo(String value) {
            addCriterion("fore_pres >=", value, "forePres");
            return (Criteria) this;
        }

        public Criteria andForePresLessThan(String value) {
            addCriterion("fore_pres <", value, "forePres");
            return (Criteria) this;
        }

        public Criteria andForePresLessThanOrEqualTo(String value) {
            addCriterion("fore_pres <=", value, "forePres");
            return (Criteria) this;
        }

        public Criteria andForePresLike(String value) {
            addCriterion("fore_pres like", value, "forePres");
            return (Criteria) this;
        }

        public Criteria andForePresNotLike(String value) {
            addCriterion("fore_pres not like", value, "forePres");
            return (Criteria) this;
        }

        public Criteria andForePresIn(List<String> values) {
            addCriterion("fore_pres in", values, "forePres");
            return (Criteria) this;
        }

        public Criteria andForePresNotIn(List<String> values) {
            addCriterion("fore_pres not in", values, "forePres");
            return (Criteria) this;
        }

        public Criteria andForePresBetween(String value1, String value2) {
            addCriterion("fore_pres between", value1, value2, "forePres");
            return (Criteria) this;
        }

        public Criteria andForePresNotBetween(String value1, String value2) {
            addCriterion("fore_pres not between", value1, value2, "forePres");
            return (Criteria) this;
        }

        public Criteria andForeLonIsNull() {
            addCriterion("fore_lon is null");
            return (Criteria) this;
        }

        public Criteria andForeLonIsNotNull() {
            addCriterion("fore_lon is not null");
            return (Criteria) this;
        }

        public Criteria andForeLonEqualTo(String value) {
            addCriterion("fore_lon =", value, "foreLon");
            return (Criteria) this;
        }

        public Criteria andForeLonNotEqualTo(String value) {
            addCriterion("fore_lon <>", value, "foreLon");
            return (Criteria) this;
        }

        public Criteria andForeLonGreaterThan(String value) {
            addCriterion("fore_lon >", value, "foreLon");
            return (Criteria) this;
        }

        public Criteria andForeLonGreaterThanOrEqualTo(String value) {
            addCriterion("fore_lon >=", value, "foreLon");
            return (Criteria) this;
        }

        public Criteria andForeLonLessThan(String value) {
            addCriterion("fore_lon <", value, "foreLon");
            return (Criteria) this;
        }

        public Criteria andForeLonLessThanOrEqualTo(String value) {
            addCriterion("fore_lon <=", value, "foreLon");
            return (Criteria) this;
        }

        public Criteria andForeLonLike(String value) {
            addCriterion("fore_lon like", value, "foreLon");
            return (Criteria) this;
        }

        public Criteria andForeLonNotLike(String value) {
            addCriterion("fore_lon not like", value, "foreLon");
            return (Criteria) this;
        }

        public Criteria andForeLonIn(List<String> values) {
            addCriterion("fore_lon in", values, "foreLon");
            return (Criteria) this;
        }

        public Criteria andForeLonNotIn(List<String> values) {
            addCriterion("fore_lon not in", values, "foreLon");
            return (Criteria) this;
        }

        public Criteria andForeLonBetween(String value1, String value2) {
            addCriterion("fore_lon between", value1, value2, "foreLon");
            return (Criteria) this;
        }

        public Criteria andForeLonNotBetween(String value1, String value2) {
            addCriterion("fore_lon not between", value1, value2, "foreLon");
            return (Criteria) this;
        }

        public Criteria andForeLatIsNull() {
            addCriterion("fore_lat is null");
            return (Criteria) this;
        }

        public Criteria andForeLatIsNotNull() {
            addCriterion("fore_lat is not null");
            return (Criteria) this;
        }

        public Criteria andForeLatEqualTo(String value) {
            addCriterion("fore_lat =", value, "foreLat");
            return (Criteria) this;
        }

        public Criteria andForeLatNotEqualTo(String value) {
            addCriterion("fore_lat <>", value, "foreLat");
            return (Criteria) this;
        }

        public Criteria andForeLatGreaterThan(String value) {
            addCriterion("fore_lat >", value, "foreLat");
            return (Criteria) this;
        }

        public Criteria andForeLatGreaterThanOrEqualTo(String value) {
            addCriterion("fore_lat >=", value, "foreLat");
            return (Criteria) this;
        }

        public Criteria andForeLatLessThan(String value) {
            addCriterion("fore_lat <", value, "foreLat");
            return (Criteria) this;
        }

        public Criteria andForeLatLessThanOrEqualTo(String value) {
            addCriterion("fore_lat <=", value, "foreLat");
            return (Criteria) this;
        }

        public Criteria andForeLatLike(String value) {
            addCriterion("fore_lat like", value, "foreLat");
            return (Criteria) this;
        }

        public Criteria andForeLatNotLike(String value) {
            addCriterion("fore_lat not like", value, "foreLat");
            return (Criteria) this;
        }

        public Criteria andForeLatIn(List<String> values) {
            addCriterion("fore_lat in", values, "foreLat");
            return (Criteria) this;
        }

        public Criteria andForeLatNotIn(List<String> values) {
            addCriterion("fore_lat not in", values, "foreLat");
            return (Criteria) this;
        }

        public Criteria andForeLatBetween(String value1, String value2) {
            addCriterion("fore_lat between", value1, value2, "foreLat");
            return (Criteria) this;
        }

        public Criteria andForeLatNotBetween(String value1, String value2) {
            addCriterion("fore_lat not between", value1, value2, "foreLat");
            return (Criteria) this;
        }

        public Criteria andForeWindLevelIsNull() {
            addCriterion("fore_wind_level is null");
            return (Criteria) this;
        }

        public Criteria andForeWindLevelIsNotNull() {
            addCriterion("fore_wind_level is not null");
            return (Criteria) this;
        }

        public Criteria andForeWindLevelEqualTo(String value) {
            addCriterion("fore_wind_level =", value, "foreWindLevel");
            return (Criteria) this;
        }

        public Criteria andForeWindLevelNotEqualTo(String value) {
            addCriterion("fore_wind_level <>", value, "foreWindLevel");
            return (Criteria) this;
        }

        public Criteria andForeWindLevelGreaterThan(String value) {
            addCriterion("fore_wind_level >", value, "foreWindLevel");
            return (Criteria) this;
        }

        public Criteria andForeWindLevelGreaterThanOrEqualTo(String value) {
            addCriterion("fore_wind_level >=", value, "foreWindLevel");
            return (Criteria) this;
        }

        public Criteria andForeWindLevelLessThan(String value) {
            addCriterion("fore_wind_level <", value, "foreWindLevel");
            return (Criteria) this;
        }

        public Criteria andForeWindLevelLessThanOrEqualTo(String value) {
            addCriterion("fore_wind_level <=", value, "foreWindLevel");
            return (Criteria) this;
        }

        public Criteria andForeWindLevelLike(String value) {
            addCriterion("fore_wind_level like", value, "foreWindLevel");
            return (Criteria) this;
        }

        public Criteria andForeWindLevelNotLike(String value) {
            addCriterion("fore_wind_level not like", value, "foreWindLevel");
            return (Criteria) this;
        }

        public Criteria andForeWindLevelIn(List<String> values) {
            addCriterion("fore_wind_level in", values, "foreWindLevel");
            return (Criteria) this;
        }

        public Criteria andForeWindLevelNotIn(List<String> values) {
            addCriterion("fore_wind_level not in", values, "foreWindLevel");
            return (Criteria) this;
        }

        public Criteria andForeWindLevelBetween(String value1, String value2) {
            addCriterion("fore_wind_level between", value1, value2, "foreWindLevel");
            return (Criteria) this;
        }

        public Criteria andForeWindLevelNotBetween(String value1, String value2) {
            addCriterion("fore_wind_level not between", value1, value2, "foreWindLevel");
            return (Criteria) this;
        }

        public Criteria andForePressureIsNull() {
            addCriterion("fore_pressure is null");
            return (Criteria) this;
        }

        public Criteria andForePressureIsNotNull() {
            addCriterion("fore_pressure is not null");
            return (Criteria) this;
        }

        public Criteria andForePressureEqualTo(String value) {
            addCriterion("fore_pressure =", value, "forePressure");
            return (Criteria) this;
        }

        public Criteria andForePressureNotEqualTo(String value) {
            addCriterion("fore_pressure <>", value, "forePressure");
            return (Criteria) this;
        }

        public Criteria andForePressureGreaterThan(String value) {
            addCriterion("fore_pressure >", value, "forePressure");
            return (Criteria) this;
        }

        public Criteria andForePressureGreaterThanOrEqualTo(String value) {
            addCriterion("fore_pressure >=", value, "forePressure");
            return (Criteria) this;
        }

        public Criteria andForePressureLessThan(String value) {
            addCriterion("fore_pressure <", value, "forePressure");
            return (Criteria) this;
        }

        public Criteria andForePressureLessThanOrEqualTo(String value) {
            addCriterion("fore_pressure <=", value, "forePressure");
            return (Criteria) this;
        }

        public Criteria andForePressureLike(String value) {
            addCriterion("fore_pressure like", value, "forePressure");
            return (Criteria) this;
        }

        public Criteria andForePressureNotLike(String value) {
            addCriterion("fore_pressure not like", value, "forePressure");
            return (Criteria) this;
        }

        public Criteria andForePressureIn(List<String> values) {
            addCriterion("fore_pressure in", values, "forePressure");
            return (Criteria) this;
        }

        public Criteria andForePressureNotIn(List<String> values) {
            addCriterion("fore_pressure not in", values, "forePressure");
            return (Criteria) this;
        }

        public Criteria andForePressureBetween(String value1, String value2) {
            addCriterion("fore_pressure between", value1, value2, "forePressure");
            return (Criteria) this;
        }

        public Criteria andForePressureNotBetween(String value1, String value2) {
            addCriterion("fore_pressure not between", value1, value2, "forePressure");
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