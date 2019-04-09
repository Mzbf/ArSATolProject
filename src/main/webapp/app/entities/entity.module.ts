import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
    imports: [
        RouterModule.forChild([
            {
                path: 'insecte-utile',
                loadChildren: './insecte-utile/insecte-utile.module#ArsatollserviceInsecteUtileModule'
            },
            {
                path: 'insecte-ravageur',
                loadChildren: './insecte-ravageur/insecte-ravageur.module#ArsatollserviceInsecteRavageurModule'
            },
            {
                path: 'maladie',
                loadChildren: './maladie/maladie.module#ArsatollserviceMaladieModule'
            },
            {
                path: 'herbe',
                loadChildren: './herbe/herbe.module#ArsatollserviceHerbeModule'
            },
            {
                path: 'culture',
                loadChildren: './culture/culture.module#ArsatollserviceCultureModule'
            },
            {
                path: 'attaque',
                loadChildren: './attaque/attaque.module#ArsatollserviceAttaqueModule'
            },
            {
                path: 'methode-lutte',
                loadChildren: './methode-lutte/methode-lutte.module#ArsatollserviceMethodeLutteModule'
            },
            {
                path: 'image-envoye',
                loadChildren: './image-envoye/image-envoye.module#ArsatollserviceImageEnvoyeModule'
            },
            {
                path: 'agriculteur',
                loadChildren: './agriculteur/agriculteur.module#ArsatollserviceAgriculteurModule'
            },
            {
                path: 'chercheur',
                loadChildren: './chercheur/chercheur.module#ArsatollserviceChercheurModule'
            },
            {
                path: 'famille',
                loadChildren: './famille/famille.module#ArsatollserviceFamilleModule'
            },
            {
                path: 'ordre',
                loadChildren: './ordre/ordre.module#ArsatollserviceOrdreModule'
            },
            {
                path: 'type-degat',
                loadChildren: './type-degat/type-degat.module#ArsatollserviceTypeDegatModule'
            },
            {
                path: 'insecte-utile',
                loadChildren: './insecte-utile/insecte-utile.module#ArsatollserviceInsecteUtileModule'
            },
            {
                path: 'insecte-ravageur',
                loadChildren: './insecte-ravageur/insecte-ravageur.module#ArsatollserviceInsecteRavageurModule'
            },
            {
                path: 'maladie',
                loadChildren: './maladie/maladie.module#ArsatollserviceMaladieModule'
            },
            {
                path: 'herbe',
                loadChildren: './herbe/herbe.module#ArsatollserviceHerbeModule'
            },
            {
                path: 'culture',
                loadChildren: './culture/culture.module#ArsatollserviceCultureModule'
            },
            {
                path: 'attaque',
                loadChildren: './attaque/attaque.module#ArsatollserviceAttaqueModule'
            },
            {
                path: 'methode-lutte',
                loadChildren: './methode-lutte/methode-lutte.module#ArsatollserviceMethodeLutteModule'
            },
            {
                path: 'image-envoye',
                loadChildren: './image-envoye/image-envoye.module#ArsatollserviceImageEnvoyeModule'
            },
            {
                path: 'agriculteur',
                loadChildren: './agriculteur/agriculteur.module#ArsatollserviceAgriculteurModule'
            },
            {
                path: 'chercheur',
                loadChildren: './chercheur/chercheur.module#ArsatollserviceChercheurModule'
            },
            {
                path: 'famille',
                loadChildren: './famille/famille.module#ArsatollserviceFamilleModule'
            },
            {
                path: 'ordre',
                loadChildren: './ordre/ordre.module#ArsatollserviceOrdreModule'
            },
            {
                path: 'type-degat',
                loadChildren: './type-degat/type-degat.module#ArsatollserviceTypeDegatModule'
            },
            {
                path: 'insecte',
                loadChildren: './insecte/insecte.module#ArsatollserviceInsecteModule'
            },
            {
                path: 'type-insecte',
                loadChildren: './type-insecte/type-insecte.module#ArsatollserviceTypeInsecteModule'
            },
            {
                path: 'insecte-utile',
                loadChildren: './insecte-utile/insecte-utile.module#ArsatollserviceInsecteUtileModule'
            },
            {
                path: 'insecte-ravageur',
                loadChildren: './insecte-ravageur/insecte-ravageur.module#ArsatollserviceInsecteRavageurModule'
            },
            {
                path: 'maladie',
                loadChildren: './maladie/maladie.module#ArsatollserviceMaladieModule'
            },
            {
                path: 'herbe',
                loadChildren: './herbe/herbe.module#ArsatollserviceHerbeModule'
            },
            {
                path: 'culture',
                loadChildren: './culture/culture.module#ArsatollserviceCultureModule'
            },
            {
                path: 'type-culture',
                loadChildren: './type-culture/type-culture.module#ArsatollserviceTypeCultureModule'
            },
            {
                path: 'zone-geo',
                loadChildren: './zone-geo/zone-geo.module#ArsatollserviceZoneGeoModule'
            },
            {
                path: 'attaque',
                loadChildren: './attaque/attaque.module#ArsatollserviceAttaqueModule'
            },
            {
                path: 'methode-lutte',
                loadChildren: './methode-lutte/methode-lutte.module#ArsatollserviceMethodeLutteModule'
            },
            {
                path: 'image-envoye',
                loadChildren: './image-envoye/image-envoye.module#ArsatollserviceImageEnvoyeModule'
            },
            {
                path: 'agriculteur',
                loadChildren: './agriculteur/agriculteur.module#ArsatollserviceAgriculteurModule'
            },
            {
                path: 'chercheur',
                loadChildren: './chercheur/chercheur.module#ArsatollserviceChercheurModule'
            },
            {
                path: 'famille',
                loadChildren: './famille/famille.module#ArsatollserviceFamilleModule'
            },
            {
                path: 'ordre',
                loadChildren: './ordre/ordre.module#ArsatollserviceOrdreModule'
            },
            {
                path: 'demo',
                loadChildren: './demo/demo.module#ArsatollserviceDemoModule'
            },
            {
                path: 'type-degat',
                loadChildren: './type-degat/type-degat.module#ArsatollserviceTypeDegatModule'
            },
            {
                path: 'insecte',
                loadChildren: './insecte/insecte.module#ArsatollserviceInsecteModule'
            },
            {
                path: 'type-insecte',
                loadChildren: './type-insecte/type-insecte.module#ArsatollserviceTypeInsecteModule'
            },
            {
                path: 'insecte-utile',
                loadChildren: './insecte-utile/insecte-utile.module#ArsatollserviceInsecteUtileModule'
            },
            {
                path: 'insecte-ravageur',
                loadChildren: './insecte-ravageur/insecte-ravageur.module#ArsatollserviceInsecteRavageurModule'
            },
            {
                path: 'maladie',
                loadChildren: './maladie/maladie.module#ArsatollserviceMaladieModule'
            },
            {
                path: 'herbe',
                loadChildren: './herbe/herbe.module#ArsatollserviceHerbeModule'
            },
            {
                path: 'culture',
                loadChildren: './culture/culture.module#ArsatollserviceCultureModule'
            },
            {
                path: 'type-culture',
                loadChildren: './type-culture/type-culture.module#ArsatollserviceTypeCultureModule'
            },
            {
                path: 'zone-geo',
                loadChildren: './zone-geo/zone-geo.module#ArsatollserviceZoneGeoModule'
            },
            {
                path: 'attaque',
                loadChildren: './attaque/attaque.module#ArsatollserviceAttaqueModule'
            },
            {
                path: 'methode-lutte',
                loadChildren: './methode-lutte/methode-lutte.module#ArsatollserviceMethodeLutteModule'
            },
            {
                path: 'image-envoye',
                loadChildren: './image-envoye/image-envoye.module#ArsatollserviceImageEnvoyeModule'
            },
            {
                path: 'agriculteur',
                loadChildren: './agriculteur/agriculteur.module#ArsatollserviceAgriculteurModule'
            },
            {
                path: 'chercheur',
                loadChildren: './chercheur/chercheur.module#ArsatollserviceChercheurModule'
            },
            {
                path: 'famille',
                loadChildren: './famille/famille.module#ArsatollserviceFamilleModule'
            },
            {
                path: 'ordre',
                loadChildren: './ordre/ordre.module#ArsatollserviceOrdreModule'
            },
            {
                path: 'demo',
                loadChildren: './demo/demo.module#ArsatollserviceDemoModule'
            },
            {
                path: 'type-degat',
                loadChildren: './type-degat/type-degat.module#ArsatollserviceTypeDegatModule'
            },
            {
                path: 'image',
                loadChildren: './image/image.module#ArsatollserviceImageModule'
            },
            {
                path: 'insecte',
                loadChildren: './insecte/insecte.module#ArsatollserviceInsecteModule'
            },
            {
                path: 'type-insecte',
                loadChildren: './type-insecte/type-insecte.module#ArsatollserviceTypeInsecteModule'
            },
            {
                path: 'insecte-utile',
                loadChildren: './insecte-utile/insecte-utile.module#ArsatollserviceInsecteUtileModule'
            },
            {
                path: 'insecte-ravageur',
                loadChildren: './insecte-ravageur/insecte-ravageur.module#ArsatollserviceInsecteRavageurModule'
            },
            {
                path: 'maladie',
                loadChildren: './maladie/maladie.module#ArsatollserviceMaladieModule'
            },
            {
                path: 'herbe',
                loadChildren: './herbe/herbe.module#ArsatollserviceHerbeModule'
            },
            {
                path: 'culture',
                loadChildren: './culture/culture.module#ArsatollserviceCultureModule'
            },
            {
                path: 'type-culture',
                loadChildren: './type-culture/type-culture.module#ArsatollserviceTypeCultureModule'
            },
            {
                path: 'zone-geo',
                loadChildren: './zone-geo/zone-geo.module#ArsatollserviceZoneGeoModule'
            },
            {
                path: 'attaque',
                loadChildren: './attaque/attaque.module#ArsatollserviceAttaqueModule'
            },
            {
                path: 'methode-lutte',
                loadChildren: './methode-lutte/methode-lutte.module#ArsatollserviceMethodeLutteModule'
            },
            {
                path: 'image-envoye',
                loadChildren: './image-envoye/image-envoye.module#ArsatollserviceImageEnvoyeModule'
            },
            {
                path: 'agriculteur',
                loadChildren: './agriculteur/agriculteur.module#ArsatollserviceAgriculteurModule'
            },
            {
                path: 'chercheur',
                loadChildren: './chercheur/chercheur.module#ArsatollserviceChercheurModule'
            },
            {
                path: 'famille',
                loadChildren: './famille/famille.module#ArsatollserviceFamilleModule'
            },
            {
                path: 'ordre',
                loadChildren: './ordre/ordre.module#ArsatollserviceOrdreModule'
            },
            {
                path: 'demo',
                loadChildren: './demo/demo.module#ArsatollserviceDemoModule'
            },
            {
                path: 'type-degat',
                loadChildren: './type-degat/type-degat.module#ArsatollserviceTypeDegatModule'
            },
            {
                path: 'image-culture',
                loadChildren: './image-culture/image-culture.module#ArsatollserviceImageCultureModule'
            },
            {
                path: 'image-insecte',
                loadChildren: './image-insecte/image-insecte.module#ArsatollserviceImageInsecteModule'
            },
            {
                path: 'image-attaque',
                loadChildren: './image-attaque/image-attaque.module#ArsatollserviceImageAttaqueModule'
            },
            {
                path: 'image-maladie',
                loadChildren: './image-maladie/image-maladie.module#ArsatollserviceImageMaladieModule'
            },
            {
                path: 'image-herbe',
                loadChildren: './image-herbe/image-herbe.module#ArsatollserviceImageHerbeModule'
            },
            {
                path: 'image',
                loadChildren: './image/image.module#ArsatollserviceImageModule'
            },
            {
                path: 'insecte',
                loadChildren: './insecte/insecte.module#ArsatollserviceInsecteModule'
            },
            {
                path: 'type-insecte',
                loadChildren: './type-insecte/type-insecte.module#ArsatollserviceTypeInsecteModule'
            },
            {
                path: 'maladie',
                loadChildren: './maladie/maladie.module#ArsatollserviceMaladieModule'
            },
            {
                path: 'herbe',
                loadChildren: './herbe/herbe.module#ArsatollserviceHerbeModule'
            },
            {
                path: 'culture',
                loadChildren: './culture/culture.module#ArsatollserviceCultureModule'
            },
            {
                path: 'type-culture',
                loadChildren: './type-culture/type-culture.module#ArsatollserviceTypeCultureModule'
            },
            {
                path: 'zone-geo',
                loadChildren: './zone-geo/zone-geo.module#ArsatollserviceZoneGeoModule'
            },
            {
                path: 'attaque',
                loadChildren: './attaque/attaque.module#ArsatollserviceAttaqueModule'
            },
            {
                path: 'methode-lutte',
                loadChildren: './methode-lutte/methode-lutte.module#ArsatollserviceMethodeLutteModule'
            },
            {
                path: 'image-envoye',
                loadChildren: './image-envoye/image-envoye.module#ArsatollserviceImageEnvoyeModule'
            },
            {
                path: 'agriculteur',
                loadChildren: './agriculteur/agriculteur.module#ArsatollserviceAgriculteurModule'
            },
            {
                path: 'chercheur',
                loadChildren: './chercheur/chercheur.module#ArsatollserviceChercheurModule'
            },
            {
                path: 'famille',
                loadChildren: './famille/famille.module#ArsatollserviceFamilleModule'
            },
            {
                path: 'ordre',
                loadChildren: './ordre/ordre.module#ArsatollserviceOrdreModule'
            },
            {
                path: 'demo',
                loadChildren: './demo/demo.module#ArsatollserviceDemoModule'
            },
            {
                path: 'type-degat',
                loadChildren: './type-degat/type-degat.module#ArsatollserviceTypeDegatModule'
            },
            {
                path: 'image-culture',
                loadChildren: './image-culture/image-culture.module#ArsatollserviceImageCultureModule'
            },
            {
                path: 'image-insecte',
                loadChildren: './image-insecte/image-insecte.module#ArsatollserviceImageInsecteModule'
            },
            {
                path: 'image-attaque',
                loadChildren: './image-attaque/image-attaque.module#ArsatollserviceImageAttaqueModule'
            },
            {
                path: 'image-maladie',
                loadChildren: './image-maladie/image-maladie.module#ArsatollserviceImageMaladieModule'
            },
            {
                path: 'image-herbe',
                loadChildren: './image-herbe/image-herbe.module#ArsatollserviceImageHerbeModule'
            },
            {
                path: 'image',
                loadChildren: './image/image.module#ArsatollserviceImageModule'
            },
            {
                path: 'insecte',
                loadChildren: './insecte/insecte.module#ArsatollserviceInsecteModule'
            },
            {
                path: 'type-insecte',
                loadChildren: './type-insecte/type-insecte.module#ArsatollserviceTypeInsecteModule'
            },
            {
                path: 'maladie',
                loadChildren: './maladie/maladie.module#ArsatollserviceMaladieModule'
            },
            {
                path: 'herbe',
                loadChildren: './herbe/herbe.module#ArsatollserviceHerbeModule'
            },
            {
                path: 'culture',
                loadChildren: './culture/culture.module#ArsatollserviceCultureModule'
            },
            {
                path: 'type-culture',
                loadChildren: './type-culture/type-culture.module#ArsatollserviceTypeCultureModule'
            },
            {
                path: 'zone-geo',
                loadChildren: './zone-geo/zone-geo.module#ArsatollserviceZoneGeoModule'
            },
            {
                path: 'attaque',
                loadChildren: './attaque/attaque.module#ArsatollserviceAttaqueModule'
            },
            {
                path: 'methode-lutte',
                loadChildren: './methode-lutte/methode-lutte.module#ArsatollserviceMethodeLutteModule'
            },
            {
                path: 'image-envoye',
                loadChildren: './image-envoye/image-envoye.module#ArsatollserviceImageEnvoyeModule'
            },
            {
                path: 'agriculteur',
                loadChildren: './agriculteur/agriculteur.module#ArsatollserviceAgriculteurModule'
            },
            {
                path: 'chercheur',
                loadChildren: './chercheur/chercheur.module#ArsatollserviceChercheurModule'
            },
            {
                path: 'famille',
                loadChildren: './famille/famille.module#ArsatollserviceFamilleModule'
            },
            {
                path: 'ordre',
                loadChildren: './ordre/ordre.module#ArsatollserviceOrdreModule'
            },
            {
                path: 'demo',
                loadChildren: './demo/demo.module#ArsatollserviceDemoModule'
            },
            {
                path: 'type-degat',
                loadChildren: './type-degat/type-degat.module#ArsatollserviceTypeDegatModule'
            },
            {
                path: 'image-culture',
                loadChildren: './image-culture/image-culture.module#ArsatollserviceImageCultureModule'
            },
            {
                path: 'image-insecte',
                loadChildren: './image-insecte/image-insecte.module#ArsatollserviceImageInsecteModule'
            },
            {
                path: 'image-attaque',
                loadChildren: './image-attaque/image-attaque.module#ArsatollserviceImageAttaqueModule'
            },
            {
                path: 'image-maladie',
                loadChildren: './image-maladie/image-maladie.module#ArsatollserviceImageMaladieModule'
            },
            {
                path: 'image-herbe',
                loadChildren: './image-herbe/image-herbe.module#ArsatollserviceImageHerbeModule'
            },
            {
                path: 'image',
                loadChildren: './image/image.module#ArsatollserviceImageModule'
            }
            /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
        ])
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ArsatollserviceEntityModule {}
