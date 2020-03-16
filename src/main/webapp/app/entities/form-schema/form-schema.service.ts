import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IFormSchema } from 'app/shared/model/form-schema.model';

type EntityResponseType = HttpResponse<IFormSchema>;
type EntityArrayResponseType = HttpResponse<IFormSchema[]>;

@Injectable({ providedIn: 'root' })
export class FormSchemaService {
  public resourceUrl = SERVER_API_URL + 'api/form-schemas';

  constructor(protected http: HttpClient) {}

  create(formSchema: IFormSchema): Observable<EntityResponseType> {
    return this.http.post<IFormSchema>(this.resourceUrl, formSchema, { observe: 'response' });
  }

  update(formSchema: IFormSchema): Observable<EntityResponseType> {
    return this.http.put<IFormSchema>(this.resourceUrl, formSchema, { observe: 'response' });
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<IFormSchema>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IFormSchema[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
