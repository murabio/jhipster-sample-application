import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { FormDataDetailComponent } from 'app/entities/form-data/form-data-detail.component';
import { FormData } from 'app/shared/model/form-data.model';

describe('Component Tests', () => {
  describe('FormData Management Detail Component', () => {
    let comp: FormDataDetailComponent;
    let fixture: ComponentFixture<FormDataDetailComponent>;
    const route = ({ data: of({ formData: new FormData('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [FormDataDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(FormDataDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(FormDataDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load formData on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.formData).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
