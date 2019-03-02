/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { AttaqueService } from 'app/entities/attaque/attaque.service';
import { IAttaque, Attaque } from 'app/shared/model/attaque.model';

describe('Service Tests', () => {
    describe('Attaque Service', () => {
        let injector: TestBed;
        let service: AttaqueService;
        let httpMock: HttpTestingController;
        let elemDefault: IAttaque;
        let currentDate: moment.Moment;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(AttaqueService);
            httpMock = injector.get(HttpTestingController);
            currentDate = moment();

            elemDefault = new Attaque(0, 'AAAAAAA', 'AAAAAAA', false, currentDate, currentDate);
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign(
                    {
                        dateValidation: currentDate.format(DATE_TIME_FORMAT),
                        dateAjout: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );
                service
                    .find(123)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: elemDefault }));

                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should create a Attaque', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0,
                        dateValidation: currentDate.format(DATE_TIME_FORMAT),
                        dateAjout: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        dateValidation: currentDate,
                        dateAjout: currentDate
                    },
                    returnedFromService
                );
                service
                    .create(new Attaque(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a Attaque', async () => {
                const returnedFromService = Object.assign(
                    {
                        localisation: 'BBBBBB',
                        description: 'BBBBBB',
                        flag: true,
                        dateValidation: currentDate.format(DATE_TIME_FORMAT),
                        dateAjout: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );

                const expected = Object.assign(
                    {
                        dateValidation: currentDate,
                        dateAjout: currentDate
                    },
                    returnedFromService
                );
                service
                    .update(expected)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'PUT' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should return a list of Attaque', async () => {
                const returnedFromService = Object.assign(
                    {
                        localisation: 'BBBBBB',
                        description: 'BBBBBB',
                        flag: true,
                        dateValidation: currentDate.format(DATE_TIME_FORMAT),
                        dateAjout: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        dateValidation: currentDate,
                        dateAjout: currentDate
                    },
                    returnedFromService
                );
                service
                    .query(expected)
                    .pipe(
                        take(1),
                        map(resp => resp.body)
                    )
                    .subscribe(body => expect(body).toContainEqual(expected));
                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify([returnedFromService]));
                httpMock.verify();
            });

            it('should delete a Attaque', async () => {
                const rxPromise = service.delete(123).subscribe(resp => expect(resp.ok));

                const req = httpMock.expectOne({ method: 'DELETE' });
                req.flush({ status: 200 });
            });
        });

        afterEach(() => {
            httpMock.verify();
        });
    });
});
