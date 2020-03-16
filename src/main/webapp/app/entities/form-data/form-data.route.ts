import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IFormData, FormData } from 'app/shared/model/form-data.model';
import { FormDataService } from './form-data.service';
import { FormDataComponent } from './form-data.component';
import { FormDataDetailComponent } from './form-data-detail.component';
import { FormDataUpdateComponent } from './form-data-update.component';

@Injectable({ providedIn: 'root' })
export class FormDataResolve implements Resolve<IFormData> {
  constructor(private service: FormDataService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IFormData> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((formData: HttpResponse<FormData>) => {
          if (formData.body) {
            return of(formData.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new FormData());
  }
}

export const formDataRoute: Routes = [
  {
    path: '',
    component: FormDataComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.formData.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: FormDataDetailComponent,
    resolve: {
      formData: FormDataResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.formData.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: FormDataUpdateComponent,
    resolve: {
      formData: FormDataResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.formData.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: FormDataUpdateComponent,
    resolve: {
      formData: FormDataResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.formData.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
