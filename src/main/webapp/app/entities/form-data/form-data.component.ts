import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IFormData } from 'app/shared/model/form-data.model';
import { FormDataService } from './form-data.service';
import { FormDataDeleteDialogComponent } from './form-data-delete-dialog.component';

@Component({
  selector: 'jhi-form-data',
  templateUrl: './form-data.component.html'
})
export class FormDataComponent implements OnInit, OnDestroy {
  formData?: IFormData[];
  eventSubscriber?: Subscription;

  constructor(protected formDataService: FormDataService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.formDataService.query().subscribe((res: HttpResponse<IFormData[]>) => (this.formData = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInFormData();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IFormData): string {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInFormData(): void {
    this.eventSubscriber = this.eventManager.subscribe('formDataListModification', () => this.loadAll());
  }

  delete(formData: IFormData): void {
    const modalRef = this.modalService.open(FormDataDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.formData = formData;
  }
}
