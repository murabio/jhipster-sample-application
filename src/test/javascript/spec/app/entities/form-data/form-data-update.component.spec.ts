import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { FormDataUpdateComponent } from 'app/entities/form-data/form-data-update.component';
import { FormDataService } from 'app/entities/form-data/form-data.service';
import { FormData } from 'app/shared/model/form-data.model';

describe('Component Tests', () => {
  describe('FormData Management Update Component', () => {
    let comp: FormDataUpdateComponent;
    let fixture: ComponentFixture<FormDataUpdateComponent>;
    let service: FormDataService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [FormDataUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(FormDataUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(FormDataUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(FormDataService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new FormData('123');
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
        const entity = new FormData();
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
