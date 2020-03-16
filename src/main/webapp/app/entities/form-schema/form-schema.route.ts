import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IFormSchema, FormSchema } from 'app/shared/model/form-schema.model';
import { FormSchemaService } from './form-schema.service';
import { FormSchemaComponent } from './form-schema.component';
import { FormSchemaDetailComponent } from './form-schema-detail.component';
import { FormSchemaUpdateComponent } from './form-schema-update.component';

@Injectable({ providedIn: 'root' })
export class FormSchemaResolve implements Resolve<IFormSchema> {
  constructor(private service: FormSchemaService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IFormSchema> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((formSchema: HttpResponse<FormSchema>) => {
          if (formSchema.body) {
            return of(formSchema.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new FormSchema());
  }
}

export const formSchemaRoute: Routes = [
  {
    path: '',
    component: FormSchemaComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.formSchema.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: FormSchemaDetailComponent,
    resolve: {
      formSchema: FormSchemaResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.formSchema.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: FormSchemaUpdateComponent,
    resolve: {
      formSchema: FormSchemaResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.formSchema.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: FormSchemaUpdateComponent,
    resolve: {
      formSchema: FormSchemaResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterSampleApplicationApp.formSchema.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
