package it.hcp.hcp4i.ms2.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.hcp.hcp4i.ms2.web.rest.TestUtil;

public class FormSchemaTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FormSchema.class);
        FormSchema formSchema1 = new FormSchema();
        formSchema1.setId("id1");
        FormSchema formSchema2 = new FormSchema();
        formSchema2.setId(formSchema1.getId());
        assertThat(formSchema1).isEqualTo(formSchema2);
        formSchema2.setId("id2");
        assertThat(formSchema1).isNotEqualTo(formSchema2);
        formSchema1.setId(null);
        assertThat(formSchema1).isNotEqualTo(formSchema2);
    }
}
