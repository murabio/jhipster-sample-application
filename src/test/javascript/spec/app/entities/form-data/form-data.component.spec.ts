import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { FormDataComponent } from 'app/entities/form-data/form-data.component';
import { FormDataService } from 'app/entities/form-data/form-data.service';
import { FormData } from 'app/shared/model/form-data.model';

describe('Component Tests', () => {
  describe('FormData Management Component', () => {
    let comp: FormDataComponent;
    let fixture: ComponentFixture<FormDataComponent>;
    let service: FormDataService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [FormDataComponent]
      })
        .overrideTemplate(FormDataComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(FormDataComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(FormDataService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new FormData('123')],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.formData && comp.formData[0]).toEqual(jasmine.objectContaining({ id: '123' }));
    });
  });
});
