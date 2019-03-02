import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
    imports: [
        RouterModule.forChild([
            {
                path: 'insecte',
                loadChildren: './insecte/insecte.module#ArsatollserviceInsecteModule'
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
                path: 'culture',
                loadChildren: './culture/culture.module#ArsatollserviceCultureModule'
            },
            {
                path: 'image',
                loadChildren: './image/image.module#ArsatollserviceImageModule'
            },
            {
                path: 'image-insecte',
                loadChildren: './image-insecte/image-insecte.module#ArsatollserviceImageInsecteModule'
            },
            {
                path: 'role',
                loadChildren: './role/role.module#ArsatollserviceRoleModule'
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
                path: 'administrateur',
                loadChildren: './administrateur/administrateur.module#ArsatollserviceAdministrateurModule'
            },
            {
                path: 'admin-insecte',
                loadChildren: './admin-insecte/admin-insecte.module#ArsatollserviceAdminInsecteModule'
            },
            {
                path: 'chercheur-insecte',
                loadChildren: './chercheur-insecte/chercheur-insecte.module#ArsatollserviceChercheurInsecteModule'
            },
            {
                path: 'super-famille',
                loadChildren: './super-famille/super-famille.module#ArsatollserviceSuperFamilleModule'
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
                path: 'insecte',
                loadChildren: './insecte/insecte.module#ArsatollserviceInsecteModule'
            },
            {
                path: 'attaque',
                loadChildren: './attaque/attaque.module#ArsatollserviceAttaqueModule'
            },
            {
                path: 'culture',
                loadChildren: './culture/culture.module#ArsatollserviceCultureModule'
            },
            {
                path: 'image',
                loadChildren: './image/image.module#ArsatollserviceImageModule'
            },
            {
                path: 'image-insecte',
                loadChildren: './image-insecte/image-insecte.module#ArsatollserviceImageInsecteModule'
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
                path: 'admin-insecte',
                loadChildren: './admin-insecte/admin-insecte.module#ArsatollserviceAdminInsecteModule'
            },
            {
                path: 'chercheur-insecte',
                loadChildren: './chercheur-insecte/chercheur-insecte.module#ArsatollserviceChercheurInsecteModule'
            },
            {
                path: 'insecte',
                loadChildren: './insecte/insecte.module#ArsatollserviceInsecteModule'
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
                path: 'culture',
                loadChildren: './culture/culture.module#ArsatollserviceCultureModule'
            },
            {
                path: 'image',
                loadChildren: './image/image.module#ArsatollserviceImageModule'
            },
            {
                path: 'image-insecte',
                loadChildren: './image-insecte/image-insecte.module#ArsatollserviceImageInsecteModule'
            },
            {
                path: 'role',
                loadChildren: './role/role.module#ArsatollserviceRoleModule'
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
                path: 'administrateur',
                loadChildren: './administrateur/administrateur.module#ArsatollserviceAdministrateurModule'
            },
            {
                path: 'admin-insecte',
                loadChildren: './admin-insecte/admin-insecte.module#ArsatollserviceAdminInsecteModule'
            },
            {
                path: 'chercheur-insecte',
                loadChildren: './chercheur-insecte/chercheur-insecte.module#ArsatollserviceChercheurInsecteModule'
            },
            {
                path: 'super-famille',
                loadChildren: './super-famille/super-famille.module#ArsatollserviceSuperFamilleModule'
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
                path: 'insecte',
                loadChildren: './insecte/insecte.module#ArsatollserviceInsecteModule'
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
                path: 'culture',
                loadChildren: './culture/culture.module#ArsatollserviceCultureModule'
            },
            {
                path: 'image',
                loadChildren: './image/image.module#ArsatollserviceImageModule'
            },
            {
                path: 'image-insecte',
                loadChildren: './image-insecte/image-insecte.module#ArsatollserviceImageInsecteModule'
            },
            {
                path: 'role',
                loadChildren: './role/role.module#ArsatollserviceRoleModule'
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
                path: 'administrateur',
                loadChildren: './administrateur/administrateur.module#ArsatollserviceAdministrateurModule'
            },
            {
                path: 'admin-insecte',
                loadChildren: './admin-insecte/admin-insecte.module#ArsatollserviceAdminInsecteModule'
            },
            {
                path: 'chercheur-insecte',
                loadChildren: './chercheur-insecte/chercheur-insecte.module#ArsatollserviceChercheurInsecteModule'
            },
            {
                path: 'super-famille',
                loadChildren: './super-famille/super-famille.module#ArsatollserviceSuperFamilleModule'
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
                path: 'insecte',
                loadChildren: './insecte/insecte.module#ArsatollserviceInsecteModule'
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
                path: 'culture',
                loadChildren: './culture/culture.module#ArsatollserviceCultureModule'
            },
            {
                path: 'image',
                loadChildren: './image/image.module#ArsatollserviceImageModule'
            },
            {
                path: 'image-insecte',
                loadChildren: './image-insecte/image-insecte.module#ArsatollserviceImageInsecteModule'
            },
            {
                path: 'role',
                loadChildren: './role/role.module#ArsatollserviceRoleModule'
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
                path: 'administrateur',
                loadChildren: './administrateur/administrateur.module#ArsatollserviceAdministrateurModule'
            },
            {
                path: 'admin-insecte',
                loadChildren: './admin-insecte/admin-insecte.module#ArsatollserviceAdminInsecteModule'
            },
            {
                path: 'chercheur-insecte',
                loadChildren: './chercheur-insecte/chercheur-insecte.module#ArsatollserviceChercheurInsecteModule'
            },
            {
                path: 'super-famille',
                loadChildren: './super-famille/super-famille.module#ArsatollserviceSuperFamilleModule'
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
                path: 'insecte',
                loadChildren: './insecte/insecte.module#ArsatollserviceInsecteModule'
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
                path: 'culture',
                loadChildren: './culture/culture.module#ArsatollserviceCultureModule'
            },
            {
                path: 'image',
                loadChildren: './image/image.module#ArsatollserviceImageModule'
            },
            {
                path: 'role',
                loadChildren: './role/role.module#ArsatollserviceRoleModule'
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
                path: 'administrateur',
                loadChildren: './administrateur/administrateur.module#ArsatollserviceAdministrateurModule'
            },
            {
                path: 'super-famille',
                loadChildren: './super-famille/super-famille.module#ArsatollserviceSuperFamilleModule'
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
                path: 'insecte',
                loadChildren: './insecte/insecte.module#ArsatollserviceInsecteModule'
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
                path: 'culture',
                loadChildren: './culture/culture.module#ArsatollserviceCultureModule'
            },
            {
                path: 'image',
                loadChildren: './image/image.module#ArsatollserviceImageModule'
            },
            {
                path: 'role',
                loadChildren: './role/role.module#ArsatollserviceRoleModule'
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
                path: 'administrateur',
                loadChildren: './administrateur/administrateur.module#ArsatollserviceAdministrateurModule'
            },
            {
                path: 'super-famille',
                loadChildren: './super-famille/super-famille.module#ArsatollserviceSuperFamilleModule'
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
                path: 'insecte',
                loadChildren: './insecte/insecte.module#ArsatollserviceInsecteModule'
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
                path: 'culture',
                loadChildren: './culture/culture.module#ArsatollserviceCultureModule'
            },
            {
                path: 'image',
                loadChildren: './image/image.module#ArsatollserviceImageModule'
            },
            {
                path: 'role',
                loadChildren: './role/role.module#ArsatollserviceRoleModule'
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
                path: 'administrateur',
                loadChildren: './administrateur/administrateur.module#ArsatollserviceAdministrateurModule'
            },
            {
                path: 'super-famille',
                loadChildren: './super-famille/super-famille.module#ArsatollserviceSuperFamilleModule'
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
                path: 'insecte',
                loadChildren: './insecte/insecte.module#ArsatollserviceInsecteModule'
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
                path: 'culture',
                loadChildren: './culture/culture.module#ArsatollserviceCultureModule'
            },
            {
                path: 'image',
                loadChildren: './image/image.module#ArsatollserviceImageModule'
            },
            {
                path: 'role',
                loadChildren: './role/role.module#ArsatollserviceRoleModule'
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
                path: 'administrateur',
                loadChildren: './administrateur/administrateur.module#ArsatollserviceAdministrateurModule'
            },
            {
                path: 'super-famille',
                loadChildren: './super-famille/super-famille.module#ArsatollserviceSuperFamilleModule'
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
                path: 'insecte',
                loadChildren: './insecte/insecte.module#ArsatollserviceInsecteModule'
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
                path: 'culture',
                loadChildren: './culture/culture.module#ArsatollserviceCultureModule'
            },
            {
                path: 'image',
                loadChildren: './image/image.module#ArsatollserviceImageModule'
            },
            {
                path: 'role',
                loadChildren: './role/role.module#ArsatollserviceRoleModule'
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
                path: 'administrateur',
                loadChildren: './administrateur/administrateur.module#ArsatollserviceAdministrateurModule'
            },
            {
                path: 'super-famille',
                loadChildren: './super-famille/super-famille.module#ArsatollserviceSuperFamilleModule'
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
                path: 'insecte',
                loadChildren: './insecte/insecte.module#ArsatollserviceInsecteModule'
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
                path: 'culture',
                loadChildren: './culture/culture.module#ArsatollserviceCultureModule'
            },
            {
                path: 'image',
                loadChildren: './image/image.module#ArsatollserviceImageModule'
            },
            {
                path: 'role',
                loadChildren: './role/role.module#ArsatollserviceRoleModule'
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
                path: 'administrateur',
                loadChildren: './administrateur/administrateur.module#ArsatollserviceAdministrateurModule'
            },
            {
                path: 'super-famille',
                loadChildren: './super-famille/super-famille.module#ArsatollserviceSuperFamilleModule'
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
                path: 'insecte',
                loadChildren: './insecte/insecte.module#ArsatollserviceInsecteModule'
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
                path: 'culture',
                loadChildren: './culture/culture.module#ArsatollserviceCultureModule'
            },
            {
                path: 'image',
                loadChildren: './image/image.module#ArsatollserviceImageModule'
            },
            {
                path: 'role',
                loadChildren: './role/role.module#ArsatollserviceRoleModule'
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
                path: 'administrateur',
                loadChildren: './administrateur/administrateur.module#ArsatollserviceAdministrateurModule'
            },
            {
                path: 'super-famille',
                loadChildren: './super-famille/super-famille.module#ArsatollserviceSuperFamilleModule'
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
                path: 'insecte',
                loadChildren: './insecte/insecte.module#ArsatollserviceInsecteModule'
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
                path: 'culture',
                loadChildren: './culture/culture.module#ArsatollserviceCultureModule'
            },
            {
                path: 'image',
                loadChildren: './image/image.module#ArsatollserviceImageModule'
            },
            {
                path: 'role',
                loadChildren: './role/role.module#ArsatollserviceRoleModule'
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
                path: 'administrateur',
                loadChildren: './administrateur/administrateur.module#ArsatollserviceAdministrateurModule'
            },
            {
                path: 'super-famille',
                loadChildren: './super-famille/super-famille.module#ArsatollserviceSuperFamilleModule'
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
                path: 'insecte',
                loadChildren: './insecte/insecte.module#ArsatollserviceInsecteModule'
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
                path: 'culture',
                loadChildren: './culture/culture.module#ArsatollserviceCultureModule'
            },
            {
                path: 'image',
                loadChildren: './image/image.module#ArsatollserviceImageModule'
            },
            {
                path: 'role',
                loadChildren: './role/role.module#ArsatollserviceRoleModule'
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
                path: 'administrateur',
                loadChildren: './administrateur/administrateur.module#ArsatollserviceAdministrateurModule'
            },
            {
                path: 'super-famille',
                loadChildren: './super-famille/super-famille.module#ArsatollserviceSuperFamilleModule'
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
                path: 'insecte',
                loadChildren: './insecte/insecte.module#ArsatollserviceInsecteModule'
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
                path: 'culture',
                loadChildren: './culture/culture.module#ArsatollserviceCultureModule'
            },
            {
                path: 'image',
                loadChildren: './image/image.module#ArsatollserviceImageModule'
            },
            {
                path: 'role',
                loadChildren: './role/role.module#ArsatollserviceRoleModule'
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
                path: 'administrateur',
                loadChildren: './administrateur/administrateur.module#ArsatollserviceAdministrateurModule'
            },
            {
                path: 'super-famille',
                loadChildren: './super-famille/super-famille.module#ArsatollserviceSuperFamilleModule'
            },
            {
                path: 'famille',
                loadChildren: './famille/famille.module#ArsatollserviceFamilleModule'
            },
            {
                path: 'ordre',
                loadChildren: './ordre/ordre.module#ArsatollserviceOrdreModule'
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
