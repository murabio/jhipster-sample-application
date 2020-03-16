import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IFormData } from 'app/shared/model/form-data.model';

type EntityResponseType = HttpResponse<IFormData>;
type EntityArrayResponseType = HttpResponse<IFormData[]>;

@Injectable({ providedIn: 'root' })
export class FormDataService {
  public resourceUrl = SERVER_API_URL + 'api/form-data';

  constructor(protected http: HttpClient) {}

  create(formData: IFormData): Observable<EntityResponseType> {
    return this.http.post<IFormData>(this.resourceUrl, formData, { observe: 'response' });
  }

  update(formData: IFormData): Observable<EntityResponseType> {
    return this.http.put<IFormData>(this.resourceUrl, formData, { observe: 'response' });
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<IFormData>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IFormData[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
