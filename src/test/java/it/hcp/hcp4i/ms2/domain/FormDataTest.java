package it.hcp.hcp4i.ms2.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import it.hcp.hcp4i.ms2.web.rest.TestUtil;

public class FormDataTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FormData.class);
        FormData formData1 = new FormData();
        formData1.setId("id1");
        FormData formData2 = new FormData();
        formData2.setId(formData1.getId());
        assertThat(formData1).isEqualTo(formData2);
        formData2.setId("id2");
        assertThat(formData1).isNotEqualTo(formData2);
        formData1.setId(null);
        assertThat(formData1).isNotEqualTo(formData2);
    }
}
