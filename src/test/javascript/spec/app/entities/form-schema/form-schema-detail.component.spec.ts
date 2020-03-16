import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { FormSchemaDetailComponent } from 'app/entities/form-schema/form-schema-detail.component';
import { FormSchema } from 'app/shared/model/form-schema.model';

describe('Component Tests', () => {
  describe('FormSchema Management Detail Component', () => {
    let comp: FormSchemaDetailComponent;
    let fixture: ComponentFixture<FormSchemaDetailComponent>;
    const route = ({ data: of({ formSchema: new FormSchema('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [FormSchemaDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(FormSchemaDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(FormSchemaDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load formSchema on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.formSchema).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
