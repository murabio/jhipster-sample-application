import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { FormSchemaUpdateComponent } from 'app/entities/form-schema/form-schema-update.component';
import { FormSchemaService } from 'app/entities/form-schema/form-schema.service';
import { FormSchema } from 'app/shared/model/form-schema.model';

describe('Component Tests', () => {
  describe('FormSchema Management Update Component', () => {
    let comp: FormSchemaUpdateComponent;
    let fixture: ComponentFixture<FormSchemaUpdateComponent>;
    let service: FormSchemaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [FormSchemaUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(FormSchemaUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(FormSchemaUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(FormSchemaService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new FormSchema('123');
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new FormSchema();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
