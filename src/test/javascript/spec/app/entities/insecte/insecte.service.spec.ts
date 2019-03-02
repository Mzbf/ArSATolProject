/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { InsecteService } from 'app/entities/insecte/insecte.service';
import { IInsecte, Insecte } from 'app/shared/model/insecte.model';

describe('Service Tests', () => {
    describe('Insecte Service', () => {
        let injector: TestBed;
        let service: InsecteService;
        let httpMock: HttpTestingController;
        let elemDefault: IInsecte;
        let currentDate: moment.Moment;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(InsecteService);
            httpMock = injector.get(HttpTestingController);
            currentDate = moment();

            elemDefault = new Insecte(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', false, currentDate, currentDate);
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

            it('should create a Insecte', async () => {
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
                    .create(new Insecte(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a Insecte', async () => {
                const returnedFromService = Object.assign(
                    {
                        nomInsecte: 'BBBBBB',
                        nomScienInsecte: 'BBBBBB',
                        cycleBio: 'BBBBBB',
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

            it('should return a list of Insecte', async () => {
                const returnedFromService = Object.assign(
                    {
                        nomInsecte: 'BBBBBB',
                        nomScienInsecte: 'BBBBBB',
                        cycleBio: 'BBBBBB',
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

            it('should delete a Insecte', async () => {
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
