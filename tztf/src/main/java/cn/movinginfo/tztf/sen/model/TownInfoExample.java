package cn.movinginfo.tztf.sen.model;

import java.util.ArrayList;
import java.util.List;

public class TownInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TownInfoExample() {
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

        public Criteria andIiiiiIsNull() {
            addCriterion("IIiii is null");
            return (Criteria) this;
        }

        public Criteria andIiiiiIsNotNull() {
            addCriterion("IIiii is not null");
            return (Criteria) this;
        }

        public Criteria andIiiiiEqualTo(String value) {
            addCriterion("IIiii =", value, "iiiii");
            return (Criteria) this;
        }

        public Criteria andIiiiiNotEqualTo(String value) {
            addCriterion("IIiii <>", value, "iiiii");
            return (Criteria) this;
        }

        public Criteria andIiiiiGreaterThan(String value) {
            addCriterion("IIiii >", value, "iiiii");
            return (Criteria) this;
        }

        public Criteria andIiiiiGreaterThanOrEqualTo(String value) {
            addCriterion("IIiii >=", value, "iiiii");
            return (Criteria) this;
        }

        public Criteria andIiiiiLessThan(String value) {
            addCriterion("IIiii <", value, "iiiii");
            return (Criteria) this;
        }

        public Criteria andIiiiiLessThanOrEqualTo(String value) {
            addCriterion("IIiii <=", value, "iiiii");
            return (Criteria) this;
        }

        public Criteria andIiiiiLike(String value) {
            addCriterion("IIiii like", value, "iiiii");
            return (Criteria) this;
        }

        public Criteria andIiiiiNotLike(String value) {
            addCriterion("IIiii not like", value, "iiiii");
            return (Criteria) this;
        }

        public Criteria andIiiiiIn(List<String> values) {
            addCriterion("IIiii in", values, "iiiii");
            return (Criteria) this;
        }

        public Criteria andIiiiiNotIn(List<String> values) {
            addCriterion("IIiii not in", values, "iiiii");
            return (Criteria) this;
        }

        public Criteria andIiiiiBetween(String value1, String value2) {
            addCriterion("IIiii between", value1, value2, "iiiii");
            return (Criteria) this;
        }

        public Criteria andIiiiiNotBetween(String value1, String value2) {
            addCriterion("IIiii not between", value1, value2, "iiiii");
            return (Criteria) this;
        }

        public Criteria andStationnameIsNull() {
            addCriterion("StationName is null");
            return (Criteria) this;
        }

        public Criteria andStationnameIsNotNull() {
            addCriterion("StationName is not null");
            return (Criteria) this;
        }

        public Criteria andStationnameEqualTo(String value) {
            addCriterion("StationName =", value, "stationname");
            return (Criteria) this;
        }

        public Criteria andStationnameNotEqualTo(String value) {
            addCriterion("StationName <>", value, "stationname");
            return (Criteria) this;
        }

        public Criteria andStationnameGreaterThan(String value) {
            addCriterion("StationName >", value, "stationname");
            return (Criteria) this;
        }

        public Criteria andStationnameGreaterThanOrEqualTo(String value) {
            addCriterion("StationName >=", value, "stationname");
            return (Criteria) this;
        }

        public Criteria andStationnameLessThan(String value) {
            addCriterion("StationName <", value, "stationname");
            return (Criteria) this;
        }

        public Criteria andStationnameLessThanOrEqualTo(String value) {
            addCriterion("StationName <=", value, "stationname");
            return (Criteria) this;
        }

        public Criteria andStationnameLike(String value) {
            addCriterion("StationName like", value, "stationname");
            return (Criteria) this;
        }

        public Criteria andStationnameNotLike(String value) {
            addCriterion("StationName not like", value, "stationname");
            return (Criteria) this;
        }

        public Criteria andStationnameIn(List<String> values) {
            addCriterion("StationName in", values, "stationname");
            return (Criteria) this;
        }

        public Criteria andStationnameNotIn(List<String> values) {
            addCriterion("StationName not in", values, "stationname");
            return (Criteria) this;
        }

        public Criteria andStationnameBetween(String value1, String value2) {
            addCriterion("StationName between", value1, value2, "stationname");
            return (Criteria) this;
        }

        public Criteria andStationnameNotBetween(String value1, String value2) {
            addCriterion("StationName not between", value1, value2, "stationname");
            return (Criteria) this;
        }

        public Criteria andItemlistIsNull() {
            addCriterion("ItemList is null");
            return (Criteria) this;
        }

        public Criteria andItemlistIsNotNull() {
            addCriterion("ItemList is not null");
            return (Criteria) this;
        }

        public Criteria andItemlistEqualTo(String value) {
            addCriterion("ItemList =", value, "itemlist");
            return (Criteria) this;
        }

        public Criteria andItemlistNotEqualTo(String value) {
            addCriterion("ItemList <>", value, "itemlist");
            return (Criteria) this;
        }

        public Criteria andItemlistGreaterThan(String value) {
            addCriterion("ItemList >", value, "itemlist");
            return (Criteria) this;
        }

        public Criteria andItemlistGreaterThanOrEqualTo(String value) {
            addCriterion("ItemList >=", value, "itemlist");
            return (Criteria) this;
        }

        public Criteria andItemlistLessThan(String value) {
            addCriterion("ItemList <", value, "itemlist");
            return (Criteria) this;
        }

        public Criteria andItemlistLessThanOrEqualTo(String value) {
            addCriterion("ItemList <=", value, "itemlist");
            return (Criteria) this;
        }

        public Criteria andItemlistLike(String value) {
            addCriterion("ItemList like", value, "itemlist");
            return (Criteria) this;
        }

        public Criteria andItemlistNotLike(String value) {
            addCriterion("ItemList not like", value, "itemlist");
            return (Criteria) this;
        }

        public Criteria andItemlistIn(List<String> values) {
            addCriterion("ItemList in", values, "itemlist");
            return (Criteria) this;
        }

        public Criteria andItemlistNotIn(List<String> values) {
            addCriterion("ItemList not in", values, "itemlist");
            return (Criteria) this;
        }

        public Criteria andItemlistBetween(String value1, String value2) {
            addCriterion("ItemList between", value1, value2, "itemlist");
            return (Criteria) this;
        }

        public Criteria andItemlistNotBetween(String value1, String value2) {
            addCriterion("ItemList not between", value1, value2, "itemlist");
            return (Criteria) this;
        }

        public Criteria andIpIsNull() {
            addCriterion("IP is null");
            return (Criteria) this;
        }

        public Criteria andIpIsNotNull() {
            addCriterion("IP is not null");
            return (Criteria) this;
        }

        public Criteria andIpEqualTo(String value) {
            addCriterion("IP =", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotEqualTo(String value) {
            addCriterion("IP <>", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpGreaterThan(String value) {
            addCriterion("IP >", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpGreaterThanOrEqualTo(String value) {
            addCriterion("IP >=", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpLessThan(String value) {
            addCriterion("IP <", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpLessThanOrEqualTo(String value) {
            addCriterion("IP <=", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpLike(String value) {
            addCriterion("IP like", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotLike(String value) {
            addCriterion("IP not like", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpIn(List<String> values) {
            addCriterion("IP in", values, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotIn(List<String> values) {
            addCriterion("IP not in", values, "ip");
            return (Criteria) this;
        }

        public Criteria andIpBetween(String value1, String value2) {
            addCriterion("IP between", value1, value2, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotBetween(String value1, String value2) {
            addCriterion("IP not between", value1, value2, "ip");
            return (Criteria) this;
        }

        public Criteria andNnnnIsNull() {
            addCriterion("NNnn is null");
            return (Criteria) this;
        }

        public Criteria andNnnnIsNotNull() {
            addCriterion("NNnn is not null");
            return (Criteria) this;
        }

        public Criteria andNnnnEqualTo(String value) {
            addCriterion("NNnn =", value, "nnnn");
            return (Criteria) this;
        }

        public Criteria andNnnnNotEqualTo(String value) {
            addCriterion("NNnn <>", value, "nnnn");
            return (Criteria) this;
        }

        public Criteria andNnnnGreaterThan(String value) {
            addCriterion("NNnn >", value, "nnnn");
            return (Criteria) this;
        }

        public Criteria andNnnnGreaterThanOrEqualTo(String value) {
            addCriterion("NNnn >=", value, "nnnn");
            return (Criteria) this;
        }

        public Criteria andNnnnLessThan(String value) {
            addCriterion("NNnn <", value, "nnnn");
            return (Criteria) this;
        }

        public Criteria andNnnnLessThanOrEqualTo(String value) {
            addCriterion("NNnn <=", value, "nnnn");
            return (Criteria) this;
        }

        public Criteria andNnnnLike(String value) {
            addCriterion("NNnn like", value, "nnnn");
            return (Criteria) this;
        }

        public Criteria andNnnnNotLike(String value) {
            addCriterion("NNnn not like", value, "nnnn");
            return (Criteria) this;
        }

        public Criteria andNnnnIn(List<String> values) {
            addCriterion("NNnn in", values, "nnnn");
            return (Criteria) this;
        }

        public Criteria andNnnnNotIn(List<String> values) {
            addCriterion("NNnn not in", values, "nnnn");
            return (Criteria) this;
        }

        public Criteria andNnnnBetween(String value1, String value2) {
            addCriterion("NNnn between", value1, value2, "nnnn");
            return (Criteria) this;
        }

        public Criteria andNnnnNotBetween(String value1, String value2) {
            addCriterion("NNnn not between", value1, value2, "nnnn");
            return (Criteria) this;
        }

        public Criteria andEeeeeIsNull() {
            addCriterion("EEEee is null");
            return (Criteria) this;
        }

        public Criteria andEeeeeIsNotNull() {
            addCriterion("EEEee is not null");
            return (Criteria) this;
        }

        public Criteria andEeeeeEqualTo(String value) {
            addCriterion("EEEee =", value, "eeeee");
            return (Criteria) this;
        }

        public Criteria andEeeeeNotEqualTo(String value) {
            addCriterion("EEEee <>", value, "eeeee");
            return (Criteria) this;
        }

        public Criteria andEeeeeGreaterThan(String value) {
            addCriterion("EEEee >", value, "eeeee");
            return (Criteria) this;
        }

        public Criteria andEeeeeGreaterThanOrEqualTo(String value) {
            addCriterion("EEEee >=", value, "eeeee");
            return (Criteria) this;
        }

        public Criteria andEeeeeLessThan(String value) {
            addCriterion("EEEee <", value, "eeeee");
            return (Criteria) this;
        }

        public Criteria andEeeeeLessThanOrEqualTo(String value) {
            addCriterion("EEEee <=", value, "eeeee");
            return (Criteria) this;
        }

        public Criteria andEeeeeLike(String value) {
            addCriterion("EEEee like", value, "eeeee");
            return (Criteria) this;
        }

        public Criteria andEeeeeNotLike(String value) {
            addCriterion("EEEee not like", value, "eeeee");
            return (Criteria) this;
        }

        public Criteria andEeeeeIn(List<String> values) {
            addCriterion("EEEee in", values, "eeeee");
            return (Criteria) this;
        }

        public Criteria andEeeeeNotIn(List<String> values) {
            addCriterion("EEEee not in", values, "eeeee");
            return (Criteria) this;
        }

        public Criteria andEeeeeBetween(String value1, String value2) {
            addCriterion("EEEee between", value1, value2, "eeeee");
            return (Criteria) this;
        }

        public Criteria andEeeeeNotBetween(String value1, String value2) {
            addCriterion("EEEee not between", value1, value2, "eeeee");
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

        public Criteria andLonEqualTo(Double value) {
            addCriterion("lon =", value, "lon");
            return (Criteria) this;
        }

        public Criteria andLonNotEqualTo(Double value) {
            addCriterion("lon <>", value, "lon");
            return (Criteria) this;
        }

        public Criteria andLonGreaterThan(Double value) {
            addCriterion("lon >", value, "lon");
            return (Criteria) this;
        }

        public Criteria andLonGreaterThanOrEqualTo(Double value) {
            addCriterion("lon >=", value, "lon");
            return (Criteria) this;
        }

        public Criteria andLonLessThan(Double value) {
            addCriterion("lon <", value, "lon");
            return (Criteria) this;
        }

        public Criteria andLonLessThanOrEqualTo(Double value) {
            addCriterion("lon <=", value, "lon");
            return (Criteria) this;
        }

        public Criteria andLonIn(List<Double> values) {
            addCriterion("lon in", values, "lon");
            return (Criteria) this;
        }

        public Criteria andLonNotIn(List<Double> values) {
            addCriterion("lon not in", values, "lon");
            return (Criteria) this;
        }

        public Criteria andLonBetween(Double value1, Double value2) {
            addCriterion("lon between", value1, value2, "lon");
            return (Criteria) this;
        }

        public Criteria andLonNotBetween(Double value1, Double value2) {
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

        public Criteria andLatEqualTo(Double value) {
            addCriterion("lat =", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatNotEqualTo(Double value) {
            addCriterion("lat <>", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatGreaterThan(Double value) {
            addCriterion("lat >", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatGreaterThanOrEqualTo(Double value) {
            addCriterion("lat >=", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatLessThan(Double value) {
            addCriterion("lat <", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatLessThanOrEqualTo(Double value) {
            addCriterion("lat <=", value, "lat");
            return (Criteria) this;
        }

        public Criteria andLatIn(List<Double> values) {
            addCriterion("lat in", values, "lat");
            return (Criteria) this;
        }

        public Criteria andLatNotIn(List<Double> values) {
            addCriterion("lat not in", values, "lat");
            return (Criteria) this;
        }

        public Criteria andLatBetween(Double value1, Double value2) {
            addCriterion("lat between", value1, value2, "lat");
            return (Criteria) this;
        }

        public Criteria andLatNotBetween(Double value1, Double value2) {
            addCriterion("lat not between", value1, value2, "lat");
            return (Criteria) this;
        }

        public Criteria andHeightIsNull() {
            addCriterion("Height is null");
            return (Criteria) this;
        }

        public Criteria andHeightIsNotNull() {
            addCriterion("Height is not null");
            return (Criteria) this;
        }

        public Criteria andHeightEqualTo(String value) {
            addCriterion("Height =", value, "height");
            return (Criteria) this;
        }

        public Criteria andHeightNotEqualTo(String value) {
            addCriterion("Height <>", value, "height");
            return (Criteria) this;
        }

        public Criteria andHeightGreaterThan(String value) {
            addCriterion("Height >", value, "height");
            return (Criteria) this;
        }

        public Criteria andHeightGreaterThanOrEqualTo(String value) {
            addCriterion("Height >=", value, "height");
            return (Criteria) this;
        }

        public Criteria andHeightLessThan(String value) {
            addCriterion("Height <", value, "height");
            return (Criteria) this;
        }

        public Criteria andHeightLessThanOrEqualTo(String value) {
            addCriterion("Height <=", value, "height");
            return (Criteria) this;
        }

        public Criteria andHeightLike(String value) {
            addCriterion("Height like", value, "height");
            return (Criteria) this;
        }

        public Criteria andHeightNotLike(String value) {
            addCriterion("Height not like", value, "height");
            return (Criteria) this;
        }

        public Criteria andHeightIn(List<String> values) {
            addCriterion("Height in", values, "height");
            return (Criteria) this;
        }

        public Criteria andHeightNotIn(List<String> values) {
            addCriterion("Height not in", values, "height");
            return (Criteria) this;
        }

        public Criteria andHeightBetween(String value1, String value2) {
            addCriterion("Height between", value1, value2, "height");
            return (Criteria) this;
        }

        public Criteria andHeightNotBetween(String value1, String value2) {
            addCriterion("Height not between", value1, value2, "height");
            return (Criteria) this;
        }

        public Criteria andHGndIsNull() {
            addCriterion("H_Gnd is null");
            return (Criteria) this;
        }

        public Criteria andHGndIsNotNull() {
            addCriterion("H_Gnd is not null");
            return (Criteria) this;
        }

        public Criteria andHGndEqualTo(String value) {
            addCriterion("H_Gnd =", value, "hGnd");
            return (Criteria) this;
        }

        public Criteria andHGndNotEqualTo(String value) {
            addCriterion("H_Gnd <>", value, "hGnd");
            return (Criteria) this;
        }

        public Criteria andHGndGreaterThan(String value) {
            addCriterion("H_Gnd >", value, "hGnd");
            return (Criteria) this;
        }

        public Criteria andHGndGreaterThanOrEqualTo(String value) {
            addCriterion("H_Gnd >=", value, "hGnd");
            return (Criteria) this;
        }

        public Criteria andHGndLessThan(String value) {
            addCriterion("H_Gnd <", value, "hGnd");
            return (Criteria) this;
        }

        public Criteria andHGndLessThanOrEqualTo(String value) {
            addCriterion("H_Gnd <=", value, "hGnd");
            return (Criteria) this;
        }

        public Criteria andHGndLike(String value) {
            addCriterion("H_Gnd like", value, "hGnd");
            return (Criteria) this;
        }

        public Criteria andHGndNotLike(String value) {
            addCriterion("H_Gnd not like", value, "hGnd");
            return (Criteria) this;
        }

        public Criteria andHGndIn(List<String> values) {
            addCriterion("H_Gnd in", values, "hGnd");
            return (Criteria) this;
        }

        public Criteria andHGndNotIn(List<String> values) {
            addCriterion("H_Gnd not in", values, "hGnd");
            return (Criteria) this;
        }

        public Criteria andHGndBetween(String value1, String value2) {
            addCriterion("H_Gnd between", value1, value2, "hGnd");
            return (Criteria) this;
        }

        public Criteria andHGndNotBetween(String value1, String value2) {
            addCriterion("H_Gnd not between", value1, value2, "hGnd");
            return (Criteria) this;
        }

        public Criteria andZoomlevelIsNull() {
            addCriterion("ZoomLevel is null");
            return (Criteria) this;
        }

        public Criteria andZoomlevelIsNotNull() {
            addCriterion("ZoomLevel is not null");
            return (Criteria) this;
        }

        public Criteria andZoomlevelEqualTo(Short value) {
            addCriterion("ZoomLevel =", value, "zoomlevel");
            return (Criteria) this;
        }

        public Criteria andZoomlevelNotEqualTo(Short value) {
            addCriterion("ZoomLevel <>", value, "zoomlevel");
            return (Criteria) this;
        }

        public Criteria andZoomlevelGreaterThan(Short value) {
            addCriterion("ZoomLevel >", value, "zoomlevel");
            return (Criteria) this;
        }

        public Criteria andZoomlevelGreaterThanOrEqualTo(Short value) {
            addCriterion("ZoomLevel >=", value, "zoomlevel");
            return (Criteria) this;
        }

        public Criteria andZoomlevelLessThan(Short value) {
            addCriterion("ZoomLevel <", value, "zoomlevel");
            return (Criteria) this;
        }

        public Criteria andZoomlevelLessThanOrEqualTo(Short value) {
            addCriterion("ZoomLevel <=", value, "zoomlevel");
            return (Criteria) this;
        }

        public Criteria andZoomlevelIn(List<Short> values) {
            addCriterion("ZoomLevel in", values, "zoomlevel");
            return (Criteria) this;
        }

        public Criteria andZoomlevelNotIn(List<Short> values) {
            addCriterion("ZoomLevel not in", values, "zoomlevel");
            return (Criteria) this;
        }

        public Criteria andZoomlevelBetween(Short value1, Short value2) {
            addCriterion("ZoomLevel between", value1, value2, "zoomlevel");
            return (Criteria) this;
        }

        public Criteria andZoomlevelNotBetween(Short value1, Short value2) {
            addCriterion("ZoomLevel not between", value1, value2, "zoomlevel");
            return (Criteria) this;
        }

        public Criteria andSelectedIsNull() {
            addCriterion("Selected is null");
            return (Criteria) this;
        }

        public Criteria andSelectedIsNotNull() {
            addCriterion("Selected is not null");
            return (Criteria) this;
        }

        public Criteria andSelectedEqualTo(Byte value) {
            addCriterion("Selected =", value, "selected");
            return (Criteria) this;
        }

        public Criteria andSelectedNotEqualTo(Byte value) {
            addCriterion("Selected <>", value, "selected");
            return (Criteria) this;
        }

        public Criteria andSelectedGreaterThan(Byte value) {
            addCriterion("Selected >", value, "selected");
            return (Criteria) this;
        }

        public Criteria andSelectedGreaterThanOrEqualTo(Byte value) {
            addCriterion("Selected >=", value, "selected");
            return (Criteria) this;
        }

        public Criteria andSelectedLessThan(Byte value) {
            addCriterion("Selected <", value, "selected");
            return (Criteria) this;
        }

        public Criteria andSelectedLessThanOrEqualTo(Byte value) {
            addCriterion("Selected <=", value, "selected");
            return (Criteria) this;
        }

        public Criteria andSelectedIn(List<Byte> values) {
            addCriterion("Selected in", values, "selected");
            return (Criteria) this;
        }

        public Criteria andSelectedNotIn(List<Byte> values) {
            addCriterion("Selected not in", values, "selected");
            return (Criteria) this;
        }

        public Criteria andSelectedBetween(Byte value1, Byte value2) {
            addCriterion("Selected between", value1, value2, "selected");
            return (Criteria) this;
        }

        public Criteria andSelectedNotBetween(Byte value1, Byte value2) {
            addCriterion("Selected not between", value1, value2, "selected");
            return (Criteria) this;
        }

        public Criteria andProvinceIsNull() {
            addCriterion("Province is null");
            return (Criteria) this;
        }

        public Criteria andProvinceIsNotNull() {
            addCriterion("Province is not null");
            return (Criteria) this;
        }

        public Criteria andProvinceEqualTo(String value) {
            addCriterion("Province =", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotEqualTo(String value) {
            addCriterion("Province <>", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThan(String value) {
            addCriterion("Province >", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThanOrEqualTo(String value) {
            addCriterion("Province >=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThan(String value) {
            addCriterion("Province <", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThanOrEqualTo(String value) {
            addCriterion("Province <=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLike(String value) {
            addCriterion("Province like", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotLike(String value) {
            addCriterion("Province not like", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceIn(List<String> values) {
            addCriterion("Province in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotIn(List<String> values) {
            addCriterion("Province not in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceBetween(String value1, String value2) {
            addCriterion("Province between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotBetween(String value1, String value2) {
            addCriterion("Province not between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andCityIsNull() {
            addCriterion("City is null");
            return (Criteria) this;
        }

        public Criteria andCityIsNotNull() {
            addCriterion("City is not null");
            return (Criteria) this;
        }

        public Criteria andCityEqualTo(String value) {
            addCriterion("City =", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotEqualTo(String value) {
            addCriterion("City <>", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThan(String value) {
            addCriterion("City >", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThanOrEqualTo(String value) {
            addCriterion("City >=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThan(String value) {
            addCriterion("City <", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThanOrEqualTo(String value) {
            addCriterion("City <=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLike(String value) {
            addCriterion("City like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotLike(String value) {
            addCriterion("City not like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityIn(List<String> values) {
            addCriterion("City in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotIn(List<String> values) {
            addCriterion("City not in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityBetween(String value1, String value2) {
            addCriterion("City between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotBetween(String value1, String value2) {
            addCriterion("City not between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andCountryIsNull() {
            addCriterion("Country is null");
            return (Criteria) this;
        }

        public Criteria andCountryIsNotNull() {
            addCriterion("Country is not null");
            return (Criteria) this;
        }

        public Criteria andCountryEqualTo(String value) {
            addCriterion("Country =", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryNotEqualTo(String value) {
            addCriterion("Country <>", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryGreaterThan(String value) {
            addCriterion("Country >", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryGreaterThanOrEqualTo(String value) {
            addCriterion("Country >=", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryLessThan(String value) {
            addCriterion("Country <", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryLessThanOrEqualTo(String value) {
            addCriterion("Country <=", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryLike(String value) {
            addCriterion("Country like", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryNotLike(String value) {
            addCriterion("Country not like", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryIn(List<String> values) {
            addCriterion("Country in", values, "country");
            return (Criteria) this;
        }

        public Criteria andCountryNotIn(List<String> values) {
            addCriterion("Country not in", values, "country");
            return (Criteria) this;
        }

        public Criteria andCountryBetween(String value1, String value2) {
            addCriterion("Country between", value1, value2, "country");
            return (Criteria) this;
        }

        public Criteria andCountryNotBetween(String value1, String value2) {
            addCriterion("Country not between", value1, value2, "country");
            return (Criteria) this;
        }

        public Criteria andAddress0IsNull() {
            addCriterion("Address0 is null");
            return (Criteria) this;
        }

        public Criteria andAddress0IsNotNull() {
            addCriterion("Address0 is not null");
            return (Criteria) this;
        }

        public Criteria andAddress0EqualTo(String value) {
            addCriterion("Address0 =", value, "address0");
            return (Criteria) this;
        }

        public Criteria andAddress0NotEqualTo(String value) {
            addCriterion("Address0 <>", value, "address0");
            return (Criteria) this;
        }

        public Criteria andAddress0GreaterThan(String value) {
            addCriterion("Address0 >", value, "address0");
            return (Criteria) this;
        }

        public Criteria andAddress0GreaterThanOrEqualTo(String value) {
            addCriterion("Address0 >=", value, "address0");
            return (Criteria) this;
        }

        public Criteria andAddress0LessThan(String value) {
            addCriterion("Address0 <", value, "address0");
            return (Criteria) this;
        }

        public Criteria andAddress0LessThanOrEqualTo(String value) {
            addCriterion("Address0 <=", value, "address0");
            return (Criteria) this;
        }

        public Criteria andAddress0Like(String value) {
            addCriterion("Address0 like", value, "address0");
            return (Criteria) this;
        }

        public Criteria andAddress0NotLike(String value) {
            addCriterion("Address0 not like", value, "address0");
            return (Criteria) this;
        }

        public Criteria andAddress0In(List<String> values) {
            addCriterion("Address0 in", values, "address0");
            return (Criteria) this;
        }

        public Criteria andAddress0NotIn(List<String> values) {
            addCriterion("Address0 not in", values, "address0");
            return (Criteria) this;
        }

        public Criteria andAddress0Between(String value1, String value2) {
            addCriterion("Address0 between", value1, value2, "address0");
            return (Criteria) this;
        }

        public Criteria andAddress0NotBetween(String value1, String value2) {
            addCriterion("Address0 not between", value1, value2, "address0");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("Type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("Type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("Type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("Type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("Type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("Type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("Type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("Type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("Type like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("Type not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("Type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("Type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("Type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("Type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andNoteIsNull() {
            addCriterion("Note is null");
            return (Criteria) this;
        }

        public Criteria andNoteIsNotNull() {
            addCriterion("Note is not null");
            return (Criteria) this;
        }

        public Criteria andNoteEqualTo(String value) {
            addCriterion("Note =", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotEqualTo(String value) {
            addCriterion("Note <>", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteGreaterThan(String value) {
            addCriterion("Note >", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteGreaterThanOrEqualTo(String value) {
            addCriterion("Note >=", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLessThan(String value) {
            addCriterion("Note <", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLessThanOrEqualTo(String value) {
            addCriterion("Note <=", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLike(String value) {
            addCriterion("Note like", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotLike(String value) {
            addCriterion("Note not like", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteIn(List<String> values) {
            addCriterion("Note in", values, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotIn(List<String> values) {
            addCriterion("Note not in", values, "note");
            return (Criteria) this;
        }

        public Criteria andNoteBetween(String value1, String value2) {
            addCriterion("Note between", value1, value2, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotBetween(String value1, String value2) {
            addCriterion("Note not between", value1, value2, "note");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("Address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("Address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("Address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("Address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("Address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("Address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("Address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("Address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("Address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("Address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("Address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("Address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("Address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("Address not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andTzairIsNull() {
            addCriterion("TZAir is null");
            return (Criteria) this;
        }

        public Criteria andTzairIsNotNull() {
            addCriterion("TZAir is not null");
            return (Criteria) this;
        }

        public Criteria andTzairEqualTo(Short value) {
            addCriterion("TZAir =", value, "tzair");
            return (Criteria) this;
        }

        public Criteria andTzairNotEqualTo(Short value) {
            addCriterion("TZAir <>", value, "tzair");
            return (Criteria) this;
        }

        public Criteria andTzairGreaterThan(Short value) {
            addCriterion("TZAir >", value, "tzair");
            return (Criteria) this;
        }

        public Criteria andTzairGreaterThanOrEqualTo(Short value) {
            addCriterion("TZAir >=", value, "tzair");
            return (Criteria) this;
        }

        public Criteria andTzairLessThan(Short value) {
            addCriterion("TZAir <", value, "tzair");
            return (Criteria) this;
        }

        public Criteria andTzairLessThanOrEqualTo(Short value) {
            addCriterion("TZAir <=", value, "tzair");
            return (Criteria) this;
        }

        public Criteria andTzairIn(List<Short> values) {
            addCriterion("TZAir in", values, "tzair");
            return (Criteria) this;
        }

        public Criteria andTzairNotIn(List<Short> values) {
            addCriterion("TZAir not in", values, "tzair");
            return (Criteria) this;
        }

        public Criteria andTzairBetween(Short value1, Short value2) {
            addCriterion("TZAir between", value1, value2, "tzair");
            return (Criteria) this;
        }

        public Criteria andTzairNotBetween(Short value1, Short value2) {
            addCriterion("TZAir not between", value1, value2, "tzair");
            return (Criteria) this;
        }

        public Criteria andSharefIsNull() {
            addCriterion("ShareF is null");
            return (Criteria) this;
        }

        public Criteria andSharefIsNotNull() {
            addCriterion("ShareF is not null");
            return (Criteria) this;
        }

        public Criteria andSharefEqualTo(String value) {
            addCriterion("ShareF =", value, "sharef");
            return (Criteria) this;
        }

        public Criteria andSharefNotEqualTo(String value) {
            addCriterion("ShareF <>", value, "sharef");
            return (Criteria) this;
        }

        public Criteria andSharefGreaterThan(String value) {
            addCriterion("ShareF >", value, "sharef");
            return (Criteria) this;
        }

        public Criteria andSharefGreaterThanOrEqualTo(String value) {
            addCriterion("ShareF >=", value, "sharef");
            return (Criteria) this;
        }

        public Criteria andSharefLessThan(String value) {
            addCriterion("ShareF <", value, "sharef");
            return (Criteria) this;
        }

        public Criteria andSharefLessThanOrEqualTo(String value) {
            addCriterion("ShareF <=", value, "sharef");
            return (Criteria) this;
        }

        public Criteria andSharefLike(String value) {
            addCriterion("ShareF like", value, "sharef");
            return (Criteria) this;
        }

        public Criteria andSharefNotLike(String value) {
            addCriterion("ShareF not like", value, "sharef");
            return (Criteria) this;
        }

        public Criteria andSharefIn(List<String> values) {
            addCriterion("ShareF in", values, "sharef");
            return (Criteria) this;
        }

        public Criteria andSharefNotIn(List<String> values) {
            addCriterion("ShareF not in", values, "sharef");
            return (Criteria) this;
        }

        public Criteria andSharefBetween(String value1, String value2) {
            addCriterion("ShareF between", value1, value2, "sharef");
            return (Criteria) this;
        }

        public Criteria andSharefNotBetween(String value1, String value2) {
            addCriterion("ShareF not between", value1, value2, "sharef");
            return (Criteria) this;
        }

        public Criteria andPropertyIsNull() {
            addCriterion("Property is null");
            return (Criteria) this;
        }

        public Criteria andPropertyIsNotNull() {
            addCriterion("Property is not null");
            return (Criteria) this;
        }

        public Criteria andPropertyEqualTo(String value) {
            addCriterion("Property =", value, "property");
            return (Criteria) this;
        }

        public Criteria andPropertyNotEqualTo(String value) {
            addCriterion("Property <>", value, "property");
            return (Criteria) this;
        }

        public Criteria andPropertyGreaterThan(String value) {
            addCriterion("Property >", value, "property");
            return (Criteria) this;
        }

        public Criteria andPropertyGreaterThanOrEqualTo(String value) {
            addCriterion("Property >=", value, "property");
            return (Criteria) this;
        }

        public Criteria andPropertyLessThan(String value) {
            addCriterion("Property <", value, "property");
            return (Criteria) this;
        }

        public Criteria andPropertyLessThanOrEqualTo(String value) {
            addCriterion("Property <=", value, "property");
            return (Criteria) this;
        }

        public Criteria andPropertyLike(String value) {
            addCriterion("Property like", value, "property");
            return (Criteria) this;
        }

        public Criteria andPropertyNotLike(String value) {
            addCriterion("Property not like", value, "property");
            return (Criteria) this;
        }

        public Criteria andPropertyIn(List<String> values) {
            addCriterion("Property in", values, "property");
            return (Criteria) this;
        }

        public Criteria andPropertyNotIn(List<String> values) {
            addCriterion("Property not in", values, "property");
            return (Criteria) this;
        }

        public Criteria andPropertyBetween(String value1, String value2) {
            addCriterion("Property between", value1, value2, "property");
            return (Criteria) this;
        }

        public Criteria andPropertyNotBetween(String value1, String value2) {
            addCriterion("Property not between", value1, value2, "property");
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