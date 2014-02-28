module TravelDream

sig Stringa {}

sig Visitatore{
	indirizzoIP: one Int
}

sig Cliente extends Visitatore{
	nome: one Stringa,
	cognome: one Stringa,
	email: one Stringa,
	password: one Stringa,
	listaPacchetti: seq PacchettoPersonalizzato
}

sig Amico {
	email: one Stringa
}

sig Impiegato {
	idImpiegato: one Stringa,
	codiceSegreto: one Stringa,
	gestiscePacchetti: set Pacchetto,
	gestisceComponenti: set Componente
}

sig Invito {
	idInvito: one Stringa,
	mittente: one Cliente,
	destinatario: one Amico, 
	pacchettoContenuto: one PacchettoPersonalizzato
}

sig Pacchetto {
	idPacchetto: one Stringa,
	creatore: one Impiegato,
	listaComponenti: some Componente
}

sig PacchettoPersonalizzato {
	idPacchettoPersonalizzato: one Stringa,
	pacchettoPredefinito: one Pacchetto
}

one sig ArchivioPacchetti {
	elencoPacchetti: some Pacchetto
}

sig Componente {
	codiceComponente: one Stringa,
	tipologiaComponente: one TipoComponente
}

enum TipoComponente {volo, hotel, escursione}

one sig ArchivioComponenti {
	elencoComponenti: some Componente
}




//non ci sono identificativi duplicati, 
//cioè non ci sono istanze della stessa entità con lo stesso identificativo
fact NoIdentificativiDuplicati{
	no disj cl1,cl2:Cliente|cl1.email=cl2.email
	no disj i1,i2:Impiegato|i1.idImpiegato=i2.idImpiegato
	no disj p1,p2:Pacchetto|p1.idPacchetto=p2.idPacchetto
	no disj pp1, pp2:PacchettoPersonalizzato|pp1.idPacchettoPersonalizzato=
																					pp2.idPacchettoPersonalizzato
	no disj c1,c2:Componente|c1.codiceComponente=c2.codiceComponente
	no disj a1,a2:Amico|a1.email=a2.email
}

//non ci sono identificativi duplicati tra diverse entità, 
//cioè non ci sono istanze di entità diverse con lo stesso identificativo
//non mettiamo la distinzione tra amico e cliente 
//perchè un amico può avere la stessa email di un cliente, se diventa cliente
fact NoIdentificativiDuplicatiDiverseEntita {
    no disj p:Pacchetto, c:Componente|p.idPacchetto=c.codiceComponente
	no disj p:Pacchetto, pp:PacchettoPersonalizzato|p.idPacchetto=
																					pp.idPacchettoPersonalizzato
	no disj c:Componente, pp:PacchettoPersonalizzato|c.codiceComponente=
																					pp.idPacchettoPersonalizzato
	no disj cl:Cliente, i:Impiegato|cl.email=i.idImpiegato
} 

//i pacchetti nella lista pacchetti del cliente non sono duplicati
//il metodo hasDups restituisce true se la lista ha elementi duplicati
fact NoDuplicatiInListaPacchetti{
		all c:Cliente | ! c.listaPacchetti.hasDups
}

//se un cliente invita più amici a visionare un pacchetto, 
//gli id degli inviti ricevuti dagli amici sono distinti
fact IdInvitoDistintiStessoPacchetto{
	some i1,i2:Invito|(i1.destinatario !=i2.destinatario) &&
	(i1.pacchettoContenuto.idPacchettoPersonalizzato=
					i2.pacchettoContenuto.idPacchettoPersonalizzato)
	implies i1.idInvito !=i2.idInvito 
}

//non si può inviare un invito a se stessi
fact NoInvitoaSeStessi {
	no i:Invito|i.mittente.email=i.destinatario.email
}

//non si possono inviare più inviti allo stesso amico per lo stesso pacchetto personalizzato
fact NoPiuInvitiStessoAmicoStessoPacchetto {
	all disj i1,i2:Invito|!(i1.mittente.email=i2.mittente.email
	&& i1.destinatario.email=i2.destinatario.email 
	&& i1.pacchettoContenuto.idPacchettoPersonalizzato=
					i2.pacchettoContenuto.idPacchettoPersonalizzato)
}

//clienti diversi non possono avere un pacchetto personalizzato con lo stesso id
fact NoStessoIdPacchettoPersonalizzatoDiversiClienti {
	no disj c1,c2:Cliente| some pp:PacchettoPersonalizzato| pp in c1.listaPacchetti.elems 
	&& pp in c2.listaPacchetti.elems
}

//per poter invitare un amico a visionare un pacchetto, 
//il cliente deve avere salvato tale pacchetto tra i suoi pacchetti
//il metodo elems restituisce il set degli elementi
fact MittenteInvitoHaPacchettoSalvato {
	all i:Invito|one c:Cliente|i.mittente=c implies (i.pacchettoContenuto in 
																								c.listaPacchetti.elems)
}

//un amico esiste solo se ha ricevuto un invito
fact AmicoHaRicevutoInvito {
	all a:Amico| some i:Invito| i.destinatario=a
}

//un pacchetto personalizzato non esiste se non è nella lista pacchetti di un cliente
fact PacchettoPersonalizzatoInListaPacchettiCliente {
	all pp:PacchettoPersonalizzato| some c:Cliente| pp in c.listaPacchetti.elems
}


//un pacchetto deve avere almeno un elemento per ogni tipologia
fact TreComponentiDiTipoDiversoInPacchetto {
	all p:Pacchetto|some c1,c2,c3:Componente |(c1+c2+c3 in p.listaComponenti) 
	&& (c1.tipologiaComponente=volo && c2.tipologiaComponente=hotel 
	&& c3.tipologiaComponente=escursione)
}

//non esiste un impiegato che non gestisce pacchetti nè componenti
fact GestisceAlmenoPacchettioComponenti {
	all i:Impiegato| !(#i.gestiscePacchetti=0 && #i.gestisceComponenti=0)
}

//un componente è in archivio oppure tra i componenti gestiti da un impiegato
fact ComponenteInArchiviooGestitoDaImpiegato {
	all c:Componente| (one ac:ArchivioComponenti|c in ac.elencoComponenti) || 
	(some i:Impiegato|c in i.gestisceComponenti)
}

//solo se un componente è in archivio può essere inserito in un pacchetto
fact ComponenteInPacchettoInArchivio {
	no c:Componente| some ac:ArchivioComponenti, p:Pacchetto| c in p.listaComponenti &&
	c not in ac.elencoComponenti
}

//un pacchetto è in archivio oppure tra i pacchetti gestiti da un impiegato
fact PacchettoInArchiviooGestitoDaImpiegato {
	all p:Pacchetto| (one ap:ArchivioPacchetti|p in ap.elencoPacchetti)|| 
	(some i:Impiegato|p in i.gestiscePacchetti)
}

//solo se un pacchetto è in archivio può essere personalizzato
fact PacchettoPersonalizzatoInArchivio {
	no p:Pacchetto| some ap:ArchivioPacchetti, pp:PacchettoPersonalizzato|
	p.idPacchetto=pp.pacchettoPredefinito.idPacchetto && p not in ap.elencoPacchetti
}

//un componente è gestito da al massimo uno e un solo impiegato alla volta
fact ComponenteGestitoDaUnSoloImpiegato {
	all c:Componente|lone i:Impiegato| c in i.gestisceComponenti
}

//un pacchetto è gestito da al massimo uno e un solo impiegato alla volta
fact PacchettoGestitoDaUnSoloImpiegato {
	all p:Pacchetto|lone i:Impiegato| p in i.gestiscePacchetti
}




	
assert A_NoIdentificativiDuplicati{
	no disj cl1,cl2:Cliente|cl1.email=cl2.email
	no disj i1,i2:Impiegato|i1.idImpiegato=i2.idImpiegato
	no disj p1,p2:Pacchetto|p1.idPacchetto=p2.idPacchetto
	no disj pp1, pp2:PacchettoPersonalizzato|pp1.idPacchettoPersonalizzato=
																					pp2.idPacchettoPersonalizzato
	no disj c1,c2:Componente|c1.codiceComponente=c2.codiceComponente
	no disj a1,a2:Amico|a1.email=a2.email
}
check A_NoIdentificativiDuplicati for 10

assert A_NoIdentificativiDuplicatiDiverseEntita{
    no disj p:Pacchetto, c:Componente|p.idPacchetto=c.codiceComponente
	no disj p:Pacchetto, pp:PacchettoPersonalizzato|p.idPacchetto=
																					pp.idPacchettoPersonalizzato
	no disj c:Componente, pp:PacchettoPersonalizzato|c.codiceComponente=
																					pp.idPacchettoPersonalizzato
	no disj cl:Cliente, i:Impiegato|cl.email=i.idImpiegato
}

check A_NoIdentificativiDuplicatiDiverseEntita for 10

assert A_NoDuplicatiInListaPacchetti{
	   all  c:Cliente | ! c.listaPacchetti.hasDups
}
check A_NoDuplicatiInListaPacchetti for 10

assert A_IdInvitoDistintiStessoPacchetto{
	some i1,i2:Invito|(i1.destinatario !=i2.destinatario) &&
	(i1.pacchettoContenuto.idPacchettoPersonalizzato=
					i2.pacchettoContenuto.idPacchettoPersonalizzato)
	implies i1.idInvito !=i2.idInvito 
}
check A_IdInvitoDistintiStessoPacchetto for 10

assert A_NoInvitoaSeStessi {
	no i:Invito|i.mittente.email=i.destinatario.email
}
check A_NoInvitoaSeStessi for 10

assert A_NoPiuInvitiStessoAmicoStessoPacchetto {
		all disj i1,i2:Invito|!(i1.mittente.email=i2.mittente.email
	&& i1.destinatario.email=i2.destinatario.email 
	&& i1.pacchettoContenuto.idPacchettoPersonalizzato=
					i2.pacchettoContenuto.idPacchettoPersonalizzato)
}
check A_NoPiuInvitiStessoAmicoStessoPacchetto for 10

assert A_NoStessoIdPacchettoPersonalizzatoDiversiClienti {
	no disj c1,c2:Cliente| some pp:PacchettoPersonalizzato| (pp in c1.listaPacchetti.elems && 
	pp in c2.listaPacchetti.elems)
}
check A_NoStessoIdPacchettoPersonalizzatoDiversiClienti for 10

assert A_MittenteInvitoHaPacchettoSalvato {
	all i:Invito|one c:Cliente|i.mittente=c implies (i.pacchettoContenuto in c.listaPacchetti.elems)
}

check A_MittenteInvitoHaPacchettoSalvato for 10

assert A_AmicoHaRicevutoInvito {
	all a:Amico| some i:Invito| i.destinatario=a
}
check  A_AmicoHaRicevutoInvito for 10

assert A_PacchettoPersonalizzatoInListaPacchettiCliente {
	all pp:PacchettoPersonalizzato| some c:Cliente| pp in c.listaPacchetti.elems
}
check A_PacchettoPersonalizzatoInListaPacchettiCliente for 10

assert A_TreComponentiDiTipoDiversoInPacchetto {
	all p:Pacchetto|some c1,c2,c3:Componente |(c1+c2+c3 in p.listaComponenti) 
	&& (c1.tipologiaComponente=volo && c2.tipologiaComponente=hotel 
	&& c3.tipologiaComponente=escursione)
}
check A_TreComponentiDiTipoDiversoInPacchetto for 10

assert A_GestisceAlmenoPacchettioComponenti {
	all i:Impiegato| !(#i.gestiscePacchetti=0 && #i.gestisceComponenti=0)
}
check A_GestisceAlmenoPacchettioComponenti for 10

assert A_ComponenteInArchiviooGestitoDaImpiegato {
	all c:Componente| (one ac:ArchivioComponenti|c in ac.elencoComponenti) || 
	(some i:Impiegato|c in i.gestisceComponenti)
}
check A_ComponenteInArchiviooGestitoDaImpiegato for 10

assert A_ComponenteInPacchettoInArchivio {
	no c:Componente| some ac:ArchivioComponenti, p:Pacchetto| c in p.listaComponenti &&
	c not in ac.elencoComponenti
}
check A_ComponenteInPacchettoInArchivio for 10

assert A_PacchettoInArchiviooGestitoDaImpiegato {
	all p:Pacchetto| (one ap:ArchivioPacchetti|p in ap.elencoPacchetti)|| 
	(some i:Impiegato|p in i.gestiscePacchetti)
}
check  A_PacchettoInArchiviooGestitoDaImpiegato for 10

assert A_PacchettoPersonalizzatoInArchivio {
	no p:Pacchetto| some ap:ArchivioPacchetti, pp:PacchettoPersonalizzato|
	p.idPacchetto=pp.pacchettoPredefinito.idPacchetto && p not in ap.elencoPacchetti
}
check A_PacchettoPersonalizzatoInArchivio for 10

assert A_ComponenteGestitoDaUnSoloImpiegato {
	all c:Componente|lone i:Impiegato| c in i.gestisceComponenti
}
check A_ComponenteGestitoDaUnSoloImpiegato for 10

assert A_PacchettoGestitoDaUnSoloImpiegato {
	all p:Pacchetto|lone i:Impiegato| p in i.gestiscePacchetti
}
check A_PacchettoGestitoDaUnSoloImpiegato for 10



//aggiunta di un pacchetto nella lista pacchetti del cliente
pred aggiungiPacchetto(c,c':Cliente, pp:PacchettoPersonalizzato){
	pp not in c.listaPacchetti.elems => c'.listaPacchetti= c.listaPacchetti.add[pp]
}
run aggiungiPacchetto for 10

//rimozione di un pacchetto dalla lista pacchetti del cliente
pred rimuoviPacchetto(c,c':Cliente,pp:PacchettoPersonalizzato){
	pp in c.listaPacchetti.elems => c'.listaPacchetti= c.listaPacchetti.delete[c.listaPacchetti.indsOf[pp]]
}
run rimuoviPacchetto for 10

//aggiunta di un pacchetto nell'archivio pacchetti
//gestisciPacchetti e gestisciComponenti sono la lista di pacchetti e di componenti che l'impiegato deve 
//aggiungere, rimuovere o modificare
pred aggiungiPacchettoInArchivio(i:Impiegato, ap,ap':ArchivioPacchetti){
	some  p:Pacchetto| p in i.gestiscePacchetti && p not in ap.elencoPacchetti implies
	ap'.elencoPacchetti= ap.elencoPacchetti + p
}
run aggiungiPacchettoInArchivio for 10

//rimozione di un pacchetto dall'archivio pacchetti
pred rimuoviPacchettoDaArchivio(i:Impiegato, ap,ap':ArchivioPacchetti){
	some  p:Pacchetto| p in i.gestiscePacchetti && p in ap.elencoPacchetti implies 
	ap'.elencoPacchetti= ap.elencoPacchetti - p
}
run rimuoviPacchettoDaArchivio for 10

//aggiunta di un componente nell'archivio componenti
pred aggiungiComponenteInArchivio(i:Impiegato, ac,ac':ArchivioComponenti){
	some  c:Componente| c in i.gestisceComponenti && c not in ac.elencoComponenti implies
	ac'.elencoComponenti= ac.elencoComponenti + c
}
run aggiungiComponenteInArchivio for 10

//rimozione di un componente dall'archivio componenti
pred rimuoviComponenteDaArchivio(i:Impiegato, ac,ac':ArchivioComponenti){
	some  c:Componente| c in i.gestisceComponenti && c in ac.elencoComponenti implies
	ac'.elencoComponenti= ac.elencoComponenti - c
}
run rimuoviComponenteDaArchivio for 10



pred show() {
	#Cliente=1
}
run show for 6
