package org.org.usurper.jpetstoredomain;

import java.io.Serializable;

public class Category implements Serializable {

    /* Private Fields */

    /**
     * 
     */
    private static final long serialVersionUID = 3062913779661349922L;
    private String categoryId;
    private String name;
    private String description;

    /* JavaBeans Properties */

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /* Public Methods */

    public String toString() {
        return getCategoryId();
    }

}
