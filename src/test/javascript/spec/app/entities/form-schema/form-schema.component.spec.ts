import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { FormSchemaComponent } from 'app/entities/form-schema/form-schema.component';
import { FormSchemaService } from 'app/entities/form-schema/form-schema.service';
import { FormSchema } from 'app/shared/model/form-schema.model';

describe('Component Tests', () => {
  describe('FormSchema Management Component', () => {
    let comp: FormSchemaComponent;
    let fixture: ComponentFixture<FormSchemaComponent>;
    let service: FormSchemaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [FormSchemaComponent]
      })
        .overrideTemplate(FormSchemaComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(FormSchemaComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(FormSchemaService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new FormSchema('123')],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.formSchemas && comp.formSchemas[0]).toEqual(jasmine.objectContaining({ id: '123' }));
    });
  });
});
