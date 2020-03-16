package it.hcp.hcp4i.ms2.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.io.Serializable;
import java.util.Objects;
import java.util.HashSet;
import java.util.Set;

/**
 * A FormSchema.
 */
@Document(collection = "form_schema")
public class FormSchema implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("definition")
    private String definition;

    @DBRef
    @Field("formData")
    private Set<FormData> formData = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDefinition() {
        return definition;
    }

    public FormSchema definition(String definition) {
        this.definition = definition;
        return this;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public Set<FormData> getFormData() {
        return formData;
    }

    public FormSchema formData(Set<FormData> formData) {
        this.formData = formData;
        return this;
    }

    public FormSchema addFormData(FormData formData) {
        this.formData.add(formData);
        formData.setSchema(this);
        return this;
    }

    public FormSchema removeFormData(FormData formData) {
        this.formData.remove(formData);
        formData.setSchema(null);
        return this;
    }

    public void setFormData(Set<FormData> formData) {
        this.formData = formData;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FormSchema)) {
            return false;
        }
        return id != null && id.equals(((FormSchema) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "FormSchema{" +
            "id=" + getId() +
            ", definition='" + getDefinition() + "'" +
            "}";
    }
}
