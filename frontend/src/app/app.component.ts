import { Component, OnInit } from '@angular/core';

interface Actividad{
  horaIni: string;
  horaFin: string;
  lugar?: string;
  mantenerFormato?: boolean;
  actividad: string;
  responsable?: Responsable[]
}
interface Responsable{
  nombre?: string;
  cargo?: string;
  ultPalabraRes?: string;
}
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit{
  today = new Date();
  menorId: number = 0;

  visionTele = false;
  visionTotem = false;

  title = 'diptico-transformacion';

  programa: Actividad[] = [ ];

  programaPrimero: Actividad[] = [
    {horaIni:'08:00', horaFin:'08:30', actividad:'Registro de participantes', mantenerFormato: true},
    {horaIni:'08:30', horaFin:'08:35', actividad:'Palabras de bienvenida',responsable:[
      {nombre: 'Dr. Edgard Miguel Siccha', cargo:'Gerente Central de Atención al Asegurado'}
    ]},
    // {horaIni:'08:35', horaFin:'08:40', actividad:'Palabras',responsable:[
    //   {nombre: 'Dr. Josué Manuel Gutiérrez Cóndor', cargo:'Defensor del Pueblo'}
    // ]},
    // {horaIni:'08:35', horaFin:'08:40', actividad:'Palabras de bienvenida',responsable:[
    //   {nombre: 'Dr. Milciades Reátegui Sánchez', cargo:'Superintendente Adjunto de la Superintendencia Adjunta de Derechos de Susalud'}
    // ]},
    {horaIni:'08:35', horaFin:'08:40', actividad:'Palabras centrales',responsable:[
      {nombre: 'Dr. Walter Borja Rojas', cargo:'Gerente General'}
    ]},
    {horaIni:'08:40', horaFin:'08:45', actividad:'Foto protocolar', mantenerFormato: true},
    {horaIni:'08:45', horaFin:'09:05', actividad:'Pre Test'},
    {horaIni:'09:05', horaFin:'09:30', actividad:'Exposición - Estrategia para la atención oportuna de reclamos en Ipress',responsable:[
      {nombre: 'Dr. Allen Aquije Díaz', cargo:'Jefe de Unidad de la Gerencia de Atención al Asegurado en Ipress'}
    ]},
    {horaIni:'09:30', horaFin:'10:15', actividad:'Exposición - Principios éticos en los servicios de atención al usuario',responsable:[
      {nombre: 'C.P.C. Ángel Daniel Casella D´alascio', cargo:'Jefe de la Oficina de Integridad '}
    ]},
    {horaIni:'10:15', horaFin:'10:30', actividad:'Break'},
    {horaIni:'10:30', horaFin:'11:15', actividad:'Exposición - Atención con enfoque en el usuario',responsable:[
      {nombre: 'Lic. Félix Manzanares Castañeda', cargo:'Jefe de Gestión de Asistencia Técnica de la Iprom de Susalud'}
    ]},
    {horaIni:'11:15', horaFin:'11:45', actividad:'Exposición - Derechos de los usuarios de los servicios de salud',responsable:[
      {nombre: 'Lic. Félix Manzanares Castañeda', cargo:'Jefe de Gestión de Asistencia Técnica de la Iprom de Susalud'}
    ]},
    {horaIni:'11:45', horaFin:'12:45', actividad:'Exposición - Reglamento de atención de reclamos en Ipress (DS 002-2019-SA)',responsable:[
      {nombre: 'Lic. Brenda Carrasco Viera', cargo:'Especialista en Implementación del Sistema de Atención al Usuario de la Iprom de Susalud'}
    ]},
    {horaIni:'12:45', horaFin:'14:30', actividad:'Almuerzo'},
    {horaIni:'14:30', horaFin:'15:15', actividad:'Exposición - Atención con enfoque intercultural',responsable:[
      {nombre: 'Sra. Inés Ojeda Reyes', cargo:'Dirección General de Ciudadanía Intercultural del Mincu'}
    ]},
    {horaIni:'15:15', horaFin:'16:00', actividad:'Exposición - Reglamento de atención de reclamos administrativos (DS 007-2020-PCM)',responsable:[
      {nombre: 'Sra. Rosa Molina Canchan', cargo: 'Especialista de Calidad de la Subsecretaría de Calidad de Servicios de PCM'},
      {nombre: 'Sr. Jaime Aliaga Vera', cargo:'Especialista de Procesos de la Subsecretaría de Calidad de Servicios de PCM'}
    ]},
    {horaIni:'16:00', horaFin:'16:15', actividad:'Break'},
    {horaIni:'16:15', horaFin:'17:00', actividad:'Exposición - Registro Informático de Atención al Asegurado (RIAA)',responsable:[
      {nombre: 'Ing. Cesar Loyola Martínez', cargo:'Sub Gerente de la Sub Gerencia del Sistema de Gestión de Atención al Asegurado'}
    ]}
  ];
  
  programaSegundo: Actividad[] = [
    {horaIni:'08:00', horaFin:'08:30', actividad:'Registro de participantes', mantenerFormato: true},
    {horaIni:'08:30', horaFin:'09:15', actividad:'Exposición - Seguridad digital',responsable:[
      {nombre: 'Ing. Paul Rivas Galloso', cargo:'Subsecretario de Políticas y Regulación Digital'}
    ]},
    {horaIni:'09:15', horaFin:'10:00', actividad:'Exposición - Atención prioritaria de pacientes con diagnóstico oncológico',responsable:[
      {nombre: 'Dr. Alejandro Figueroa Torrejón', cargo:'Jefe del Servicio de Oncología del Hospital Nacional Edgardo Rebagliati Martins'}
    ]},
    {horaIni:'10:00', horaFin:'10:45', actividad:'Exposición - Metodología Lean para la mejora de procesos',responsable:[
      {nombre: 'Dr. Amer Ali Martinez Milla', cargo:'Gerente de acreditación y mejora continua de la calidad'}
    ]},
    {horaIni:'10:45', horaFin:'11:00', actividad:'Break'},
    {horaIni:'11:00', horaFin:'13:00', actividad:'Taller - Habilidades blandas, comunicación efectiva, asertividad, empatía y trabajo en equipo',responsable:[
      {nombre: 'Lic. Violeta Alvarado Ríos', cargo:'Jefa del Departamento de Psicología del Hospital Nacional Guillermo Almenara Irigoyen'}
    ]},
    {horaIni:'13:00', horaFin:'14:20', actividad:'Almuerzo'},
    {horaIni:'14:20', horaFin:'16:20', actividad:'Taller - Criterios para la elección de motivos de reclamo en el RIAA',responsable:[
      {nombre: 'Lic. Alfonso López Mori', cargo:'Sectorista de la región Centro de la Gerencia de Atención al Asegurado en Ipress'},
      {nombre: 'Lic. Luis García Tello', cargo:'Sectorista de la región Norte de la Gerencia de Atención al Asegurado en Ipress'}
    ]},
    {horaIni:'16:20', horaFin:'16:40', actividad:'Taller - Propuestas de mejora',responsable:[
      {nombre: 'Dr. Christian Jara Campos', cargo:'Jefe de Unidad de la Gerencia de Atención al Asegurado'}
    ]},
    {horaIni:'16:40', horaFin:'16:50', actividad:'Post Test'},
    {horaIni:'16:50', horaFin:'17:00', actividad:'Clausura',responsable:[
      {nombre: 'Dra. Roxana Palacios Vildoso', cargo:'Gerente de Atención al Asegurado '}
    ]},
  ];
  

  // programaPrimero: Actividad[] = [
  //   {horaIni:'08:00', horaFin:'08:30', actividad:'Registro de participantes', mantenerFormato: true},
  //   {horaIni:'08:30', horaFin:'08:35', actividad:'Palabras de bienvenida',responsable:[
  //     {nombre: 'Dr. Edgard Miguel Siccha', cargo:'Gerente Central de Atención al Asegurado'}
  //   ]},
  //   {horaIni:'08:35', horaFin:'08:40', actividad:'Palabras',responsable:[
  //     {nombre: 'Dr. Josué Manuel Gutiérrez Cóndor', cargo:'Defensor del Pueblo'}
  //   ]},
  //   {horaIni:'08:40', horaFin:'08:45', actividad:'Palabras',responsable:[
  //     {nombre: 'Dr. Milciades Reátegui Sánchez', cargo:'Superintendente Adjunto de la Superintendencia Adjunta de Derechos de Susalud'}
  //   ]},
  //   {horaIni:'08:45', horaFin:'08:50', actividad:'Palabras centrales',responsable:[
  //     {nombre: 'Dr. César Linares Aguilar', cargo:'Presidente Ejecutivo'}
  //   ]},
  //   {horaIni:'08:50', horaFin:'08:50', actividad:'Foto protocolar', mantenerFormato: true},
  //   {horaIni:'08:50', horaFin:'09:00', actividad:'Pre Test', mantenerFormato: true},
  //   {horaIni:'09:00', horaFin:'09:30', actividad:'Exposición - Estrategia para la atención oportuna de reclamos en Ipress',responsable:[
  //     {nombre: 'Dr. Allen Aquije Díaz', cargo:'Jefe de Unidad de la Gerencia de Atención al Asegurado en Ipress'}
  //   ]},
  //   {horaIni:'09:30', horaFin:'10:15', actividad:'Exposición - Principios éticos en los servicios de atención al usuario',responsable:[
  //     {nombre: 'C.P.C. Ángel Daniel Casella D´alascio', cargo:'Jefe de la Oficina de Integridad '}
  //   ]},
  //   {horaIni:'10:15', horaFin:'10:30', actividad:'Break'},
  //   {horaIni:'10:30', horaFin:'11:15', actividad:'Exposición - Atención con enfoque en el usuario',responsable:[
  //     {nombre: 'Lic. Félix Manzanares Castañeda', cargo:'Jefe de Gestión de Asistencia Técnica de la Intendencia de Promoción de Derechos en Salud de la Superintendencia Nacional de Salud'}
  //   ]},
  //   {horaIni:'11:15', horaFin:'11:45', actividad:'Exposición - Derechos de los usuarios de los servicios de salud',responsable:[
  //     {nombre: 'Lic. Félix Manzanares Castañeda', cargo:'Jefe de Gestión de Asistencia Técnica de la Intendencia de Promoción de Derechos en Salud de la Superintendencia Nacional de Salud'}
  //   ]},
  //   {horaIni:'11:45', horaFin:'12:45', actividad:'Exposición - Reglamento de atención de reclamos en Ipress (DS 002-2019-SA)',responsable:[
  //     {nombre: 'Lic. Brenda Carrasco Viera', cargo:'Especialista en Implementación del Sistema de Atención al Usuario de la Intendencia de Promoción de Derechos en Salud de la Superintendencia Nacional de Salud'}
  //   ]},
  //   {horaIni:'12:45', horaFin:'14:30', actividad:'Almuerzo'},
  //   {horaIni:'14:30', horaFin:'15:15', actividad:'Exposición - Atención con enfoque intercultural',responsable:[
  //     {nombre: 'Sra. Inés Ojeda Reyes', cargo:'Representante del Vice Ministerio de Interculturalidad del Ministerio de Cultura'}
  //   ]},
  //   {horaIni:'15:15', horaFin:'16:00', actividad:'Exposición - Reglamento de atención de reclamos administrativos (DS 007-2020-PCM)',responsable:[
  //     {nombre: 'Sra. Rosa Molina Canchan'},
  //     {nombre: 'Sr. Jaime Aliaga Vera', cargo:'Representante de la Secretaría de Gestión Pública de la Presidencia del Consejo de Ministros'}
  //   ]},
  //   {horaIni:'16:00', horaFin:'16:15', actividad:'Break'},
  //   {horaIni:'16:15', horaFin:'17:00', actividad:'Exposición - Registro Informático de Atención al Asegurado (RIAA)',responsable:[
  //     {nombre: 'Ing. Cesar Loyola Martínez', cargo:'Sub Gerente de la Sub Gerencia del Sistema de Gestión de Atención al Asegurado'}
  //   ]}
  // ];
  
  // programaSegundo: Actividad[] = [
  //   {horaIni:'08:00', horaFin:'08:30', actividad:'Registro de participantes', mantenerFormato: true},
  //   {horaIni:'08:30', horaFin:'09:15', actividad:'Exposición - Seguridad digital',responsable:[
  //     {nombre: 'Ing. Alain Donuhue Dongo Quintana', cargo:'Secretario de la Secretaria de Gobierno y Transformación Digital de la Presidencia del Consejo de Ministros'}
  //   ]},
  //   {horaIni:'09:15', horaFin:'10:00', actividad:'Exposición - Atención prioritaria de pacientes con diagnóstico oncológico',responsable:[
  //     {nombre: 'Dr. Alejandro Figueroa Torrejón', cargo:'Jefe del Servicio de Oncología del Hospital Nacional Edgardo Rebagliati Martins'}
  //   ]},
  //   {horaIni:'10:00', horaFin:'10:45', actividad:'Exposición - Metodología Lean para la mejora de procesos',responsable:[
  //     {nombre: 'Dr. Amer Ali Martinez Milla', cargo:'Gerente de acreditación y mejora continua de la calidad'}
  //   ]},
  //   {horaIni:'10:45', horaFin:'11:00', actividad:'Break'},
  //   {horaIni:'11:00', horaFin:'13:00', actividad:'Taller - Habilidades blandas, comunicación efectiva, asertividad, empatía y trabajo en equipo',responsable:[
  //     {nombre: 'Lic. Violeta Alvarado Ríos', cargo:'Jefa del Departamento de Psicología del Hospital Nacional Guillermo Almenara Irigoyen'}
  //   ]},
  //   {horaIni:'13:00', horaFin:'14:20', actividad:'Almuerzo'},
  //   {horaIni:'14:20', horaFin:'16:20', actividad:'Taller - Criterios para la elección de motivos de reclamo en el RIAA',responsable:[
  //     {nombre: 'Lic. Alfonso López Mori', cargo:'Sectorista de la región Centro de la Gerencia de Atención al Asegurado en Ipress'},
  //     {nombre: 'Lic. Luis García Tello', cargo:'Sectorista de la región Norte de la Gerencia de Atención al Asegurado en Ipress'}
  //   ]},
  //   {horaIni:'16:20', horaFin:'16:40', actividad:'Taller - Propuestas de mejora',responsable:[
  //     {nombre: 'Dr. Christian Jara Campos', cargo:'Jefe de Unidad de la Gerencia de Atención al Asegurado en Ipress'}
  //   ]},
  //   {horaIni:'16:40', horaFin:'16:50', actividad:'Post Test', mantenerFormato: true},
  //   {horaIni:'16:50', horaFin:'17:00', actividad:'Conclusiones de la Jornada y Clausura',responsable:[
  //     {nombre: 'Dra. Roxana Palacios Vildoso', cargo:'Gerente de Atención al Asegurado en Ipress'}
  //   ]},
  // ];
  
  opcionesDias = [{dia: 28, descripcionFecha: 'de setiembre 2023', activo: false, programaDia: this.programaPrimero}, {dia: 29, descripcionFecha: 'de setiembre 2023', activo: false, programaDia: this.programaSegundo}];

  // programa: Actividad[] = [
  //   {horaIni: '8:00',horaFin:'8:30', actividad: 'Recepción y acreditación', responsable: [
  //     {cargo:'Personal de la Oficina de Cooperación Internacional'}
  //   ]},
  //   {horaIni: '8:30',horaFin:'8:40', actividad: 'Bienvenida', responsable: [
  //     {nombre: 'Lic. Sitza Romero Peralta', cargo:'Jefe de la Oficina de Cooperación Internacional EsSalud'}]},
  //   {horaIni: '8:40',horaFin:'8:50', actividad: 'Inauguración', responsable: [{nombre: 'Dr. César Linares Aguilar', cargo:'Presidente Ejecutivo EsSalud'}
  // ]},
  //   {horaIni: '8:50',horaFin:'9:00', actividad: 'Palabras', responsable: [
  //     {nombre: 'Dr. Ciro Abel Mestas Valero', cargo:'Viceministro del Despacho Viceministerial de Prestaciones y Aseguramiento en Salud'},
  //     {nombre: 'Ministerio de Salud - MINSA'}
  //   ]},
  //   {horaIni: '9:00',horaFin:'9:20', actividad: 'Avances y Retos de la Telesalud', responsable: [
  //     {nombre: 'Dr. Pedro Jonel Ripalda Ramírez/ Perú', cargo:'Director General de Telesalud, Referencia y Urgencias Responsable de Transformación Digital'},
  //     {nombre: 'Ministerio de Salud - MINSA'}
  //   ]},
  //   {horaIni: '9:20',horaFin:'9:40', actividad: 'Experiencia Hospital Digital El Salvador', responsable: [
  //     {nombre: 'Dr. Manuel Enrique Bello iQuezada: CAF/ El Salvador', cargo:'Jefe de Departamento de Terapia Intensiva del Hospital Nacional del Salvador'},
  //     {nombre: 'Ministerio de Salud - MINSAL'}
  //   ]},
  //   {horaIni: '9:40',horaFin:'9:50', actividad: 'Experiencia Hospital Digital El Salvador', responsable: [
  //     {nombre: 'Dra. Laura Estela Miranda Iraheta: CAF/ El Salvador', cargo:'Directora del Hospital Nacional El Salvador'},
  //     {nombre: 'Ministerio de Salud - MINSAL'}
  //   ]},
  //   {horaIni: '9:50',horaFin:'10:10', actividad: 'Transformación Digital y Telesalud: Potenciando la Toma de Decisiones en Salud', responsable: [
  //     {nombre: 'Dr. Juan Alberto Santillana Callirgos / Perú', cargo:'Director del Instituto de Evaluación de Tecnologías en Salud e Investigación (IETSI) - ', ultPalabraRes: 'EsSalud'}
  //   ]},
  //   {horaIni: '10:10',horaFin:'10:25', actividad: 'COFFEE BREAK'},
  //   {horaIni: '10:25',horaFin:'10:45', actividad: 'Experiencia de la Telemedicina en México', responsable: [
  //     {nombre: 'Lic. Yibran Alejandro Hernández Montoya', cargo:'Jefe del Departamento de Integración de Tecnología Médica para la Telemedicina Cenetec - Salud'},
  //     {nombre: 'Secretaría de Salud Federal de México'}
  //   ]},
  //   {horaIni: '10:45',horaFin:'11:30',lugar:'Conversatorio Internacional 1', actividad: 'Estrategias y Retos para la Implementación de Hospitales Virtuales', responsable: [
  //     {nombre: 'Dr. Fernando Plazzotta: Argentina/BID', cargo:'Jefe de Informática para la Comunidad', ultPalabraRes: 'Hospital Italiano de Buenos Aires Argentina'},
  //     {nombre: 'Dra. Sandra Gallegos/ Colombia', cargo:'Ministerio de Salud de Colombia'},
  //     {nombre: 'Mag. Elisa Martinez Luaces: BID - Udelar/ Uruguay', cargo:'Consultora externa en gestión del cambio para América Latina y el Caribe'},
  //     {nombre: 'Ing. Frank Jonathan Guzmán Castillo/ Perú', cargo:'Gerente Central de la Gerencia Central de Tecnologías de Información y Comunicaciones de EsSalud'}
  //   ]},
  //   {horaIni: '11:30',horaFin:'11:50', actividad: 'Gestión de la Calidad de los Servicios en Telesalud', responsable: [
  //     {nombre: 'Dr. Fernando Plazzotta: BID/ Argentina', cargo:'Jefe de Informática para la Comunidad', ultPalabraRes: 'Hospital Italiano de Buenos Aires Argentina'}
  //   ]},
  //   {horaIni: '11:50',horaFin:'12:10', actividad: 'Gestión del Cambio para Impulsar la Salud Digital', responsable: [
  //     {nombre: 'Mag. Elisa Martinez Luaces: BID - Udelar/ Uruguay', cargo:'Consultora externa en gestión del cambio para América Latina y el Caribe'},
  //     {cargo:'División Salud y Protección Social, Banco Interamericano de Desarrollo'},
  //     {cargo:'Coordinadora General de Proyecto ECHO'},
  //     {cargo:'Facultad de Medicina, Universidad de la República'}
  //   ]},
  //   {horaIni: '12:10',horaFin:'12:30', actividad: 'Experiencia de la Telemedicina en EsSalud', responsable: [
  //     {nombre: 'Dra. Bernardette Isabel Cotrina Urteaga/ Perú', cargo:'Directora del Centro Nacional de Telemedicina (CENATE) - ', ultPalabraRes: 'EsSalud'}
  //   ]},
  //   {horaIni: '12:30',horaFin:'12:50', actividad: 'Uso de la Inteligencia Artificial en la Enfermedad Renal Crónica', responsable: [
  //     {nombre: 'Dr. Walberto Buelvas/ Colombia', cargo:'Director de Gestión Clínica e Innovación en Salud'},
  //     {nombre: 'Medisinu IPS'}
  //   ]},
  //   {horaIni: '12:50',horaFin:'13:10', actividad: 'Avances en el Uso de la Telemedicina en Latinoamérica', responsable: [
  //     {nombre: 'Lic. Myrna Carolina Marti: OPS-OMS/ EEUU', cargo:'Consultora en Sistemas de Información y Salud Digital de la OPS/OMS'},
  //   ]},
  //   {horaIni: '13:10',horaFin:'13:30', actividad: 'Experiencias de la Telesalud en los Países Andinos', responsable: [
  //     {nombre: 'Dr. Bogi Eliasen/Dinamarca', cargo:'Director del Copenhagen Institute for futures Studies Organismo Andino de Salud - Convenio Hipólito Unanue'},
  //   ]},
  //   {horaIni: '13:30',horaFin:'14:30', actividad: 'RECESO'},
  //   {horaIni: '14:30',horaFin:'14:50', actividad: 'Transformación Digital y Gobernanza en el Perú', responsable: [
  //     {nombre: 'Ing. Alain Donuhue Dongo Quintana/ Perú', cargo:'Secretario de Gobierno y Transformación Digital'},
  //     {nombre: 'Presidencia de Consejo de Ministros - PCM', cargo:'Responsable de Transformación Digital en Perú'}
  //   ]},
  //   {horaIni: '14:50',horaFin:'15:35', lugar:'Conversatorio Internacional 2', actividad: 'Inteligencia Artificial', responsable: [
  //     {nombre: 'Dr. Julio Valdivia Silva/ Perú', cargo:'Universidad UTEC Experiencia Proyecto Promedius - Universidad UTEC/Perú'},
  //     {nombre: 'Dr. Walberto Buelvas/ Colombia ', cargo:'Director de Gestión Clínica e Innovación en Salud Medisinu IPS'},
  //     {cargo:'Experiencia Proyecto Qure.ai - Reino Unido'},
  //     {nombre: 'Dr. Edgar Juan Coila Paricahua/ Perú', cargo:'Subdirección de Desarrollo de Investigación en Salud - IETSI'},
  //     {nombre: 'Dr. Héctor Upegui, MD/ Alemania', cargo:'Chief Health Officer & Cúram by Merative'},
  //     {cargo:'Colaborador de la Asociación Internacional de la Seguridad Social - AISS'},
  //     {nombre: 'Dra. Verónica Xhardez/ Argentina', cargo:'Coordinadora Técnica del Proyecto Gestión Epidemiológica basada en Inteligencia Artificial y Ciencia de Datos'},
  //     {cargo:'Experto de la Conferencia Interamericana de Seguridad Social -CISS'}
  //   ]},
  //   {horaIni: '15:35',horaFin:'15:55', actividad: 'Herramientas globales para la Transformación Digital', responsable: [
  //     {nombre: 'Mg. Lais Gondim de A. Paixao/ Brasil', cargo:'Experto de la Corporación Financiera del Banco Mundial / IFC-BM'}
  //   ]},
  //   {horaIni: '15:55',horaFin:'16:00', actividad: 'Clausura del Evento', responsable: [
  //     {nombre: 'Dra. Catalina Onuma Cairampoma', cargo:'Gerente General de la Seguridad Social de Salud - ', ultPalabraRes: 'EsSalud'}
  //   ]}
  // ];
  constructor( ) {
  }

  //supuestamente 7196 width y 4047 height
  ngOnInit(): void {
    if (this.today.getDate() == 29) {
      this.opcionesDias.find((x) => {return x.dia == 29})!.activo = true;
      this.programa = this.programaSegundo;
    }
    else{
      this.opcionesDias[0].activo = true;
      this.programa = this.programaPrimero;
    }

    if (window.innerWidth >= 1500) {
      this.visionTele = true
    }

    this.selectNearest();

    if(window.innerHeight >= 1500){
      this.visionTotem = true;
    }

    console.log(window.innerWidth);
    console.log(window.innerHeight);
  }
  ngAfterViewChecked(): void{
    if (window.innerWidth < 1500) {
      this.scroll(document.getElementById(this.menorId.toString())!);
      setInterval(() => {
        if(this.today.getDate() == 29){
          this.opcionesDias.map((x)=> x.activo = false);
          this.opcionesDias[1].activo = true;
          this.programa = this.opcionesDias[1].programaDia;
        }
        else{
          this.opcionesDias.map((x)=> x.activo = false);
          this.opcionesDias[0].activo = true;
          this.programa = this.opcionesDias[0].programaDia;
        }
        this.selectNearest();
        this.scroll(document.getElementById(this.menorId.toString())!);
      }, 120000); // Mil es 1 seg
    }
    else{
      setInterval(() => {
        this.selectNearest();
      }, 120000); // Mil es 1 seg
    }
  }
  scroll(el: HTMLElement) {
    el.scrollIntoView({
      behavior: 'auto',
      block: 'center',
      inline: 'center'
  });
  }

  selectableOpt(index: number){
    this.programa = this.opcionesDias[index].programaDia;
    this.opcionesDias.map((x)=> x.activo = false);
    this.opcionesDias[index].activo = true;
    this.selectNearest();
    console.log(document.getElementById(this.menorId.toString()))
    this.scroll(document.getElementById(this.menorId.toString())!);
  }

  selectNearest(){
    const hoy = new Date();
    for (let i = 0; i < this.programa.length; i++) {
      const horaEnd = Number(this.programa[i].horaFin.split(':')[0]);
      const minEnd = Number(this.programa[i].horaFin.split(':')[1]);
      if (horaEnd >= hoy.getHours()) {
        if(horaEnd == hoy.getHours() && minEnd > hoy.getMinutes()){
          this.menorId = i;
          break;
        }
        if(horaEnd > hoy.getHours()){
          this.menorId = i;
          break;
        }
      }
    }
  }
}
