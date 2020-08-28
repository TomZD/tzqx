package cn.movinginfo.tztf.sev.domain;

import javax.persistence.Column;
import javax.persistence.Table;

import cn.movinginfo.tztf.common.domain.BaseDomain;

@Table(name = "sev_t_sensitive")
public class Sensitive extends BaseDomain {

    @Column(name = "word")
    private String word;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
