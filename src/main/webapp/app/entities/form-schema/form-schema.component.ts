import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IFormSchema } from 'app/shared/model/form-schema.model';
import { FormSchemaService } from './form-schema.service';
import { FormSchemaDeleteDialogComponent } from './form-schema-delete-dialog.component';

@Component({
  selector: 'jhi-form-schema',
  templateUrl: './form-schema.component.html'
})
export class FormSchemaComponent implements OnInit, OnDestroy {
  formSchemas?: IFormSchema[];
  eventSubscriber?: Subscription;

  constructor(protected formSchemaService: FormSchemaService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.formSchemaService.query().subscribe((res: HttpResponse<IFormSchema[]>) => (this.formSchemas = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInFormSchemas();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IFormSchema): string {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInFormSchemas(): void {
    this.eventSubscriber = this.eventManager.subscribe('formSchemaListModification', () => this.loadAll());
  }

  delete(formSchema: IFormSchema): void {
    const modalRef = this.modalService.open(FormSchemaDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.formSchema = formSchema;
  }
}
