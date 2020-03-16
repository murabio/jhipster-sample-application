package it.hcp.hcp4i.ms2.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.io.Serializable;
import java.util.Objects;

/**
 * A FormData.
 */
@Document(collection = "form_data")
public class FormData implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("payload")
    private String payload;

    @DBRef
    @Field("author")
    @JsonIgnoreProperties("formData")
    private Employee author;

    @DBRef
    @Field("schema")
    @JsonIgnoreProperties("formData")
    private FormSchema schema;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPayload() {
        return payload;
    }

    public FormData payload(String payload) {
        this.payload = payload;
        return this;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public Employee getAuthor() {
        return author;
    }

    public FormData author(Employee employee) {
        this.author = employee;
        return this;
    }

    public void setAuthor(Employee employee) {
        this.author = employee;
    }

    public FormSchema getSchema() {
        return schema;
    }

    public FormData schema(FormSchema formSchema) {
        this.schema = formSchema;
        return this;
    }

    public void setSchema(FormSchema formSchema) {
        this.schema = formSchema;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FormData)) {
            return false;
        }
        return id != null && id.equals(((FormData) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "FormData{" +
            "id=" + getId() +
            ", payload='" + getPayload() + "'" +
            "}";
    }
}
