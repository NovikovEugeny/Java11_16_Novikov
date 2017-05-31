insert into user values 
	(1, 'admin', 'Novikov', 'Eugeny', 'Vitalievich', '+375293757252', 'da4b83a784b02d70f5fdd100ae09c9fe', 'yes'),
	(2, 'pharmacist', 'Ivanova', 'Irina', 'Vladimirovna', '+375291111111', '181b6444ac5c6686a4efda3b4549ffde', 'yes'),
	(3, 'pharmacist', 'Petrova', 'Anna', 'Borisovna', '+375292222222', '66ebbdf8b3edc04d4bc74e8407b74b92', 'yes'),
	(4, 'doctor', 'Socolova', 'Svetlana', 'Victorovna', '+375293333333', '21473908bc7b74d287662fd5f5608330', 'yes'),
	(5, 'client', 'Sinicin', 'Oleg', 'Ivanovich', '+375294444444', 'f5701420e97b2c107eceb62a8ef1953e', 'yes'),
	(6, 'client', 'Soroca', 'Nikolai', 'Ivanovich', '+375295555555', '5752c39f7e6b35885483b115afe559c7', 'yes'),
	(7, 'client', 'Prudbikov', 'Semen', 'Borisovich', '+375296666666', 'a6400b2372ea26025a3a95386ee26665', 'yes'),
	(8, 'client', 'Fedorov', 'Gleb', 'Timofeevich', '+375297777777', '4669e4aed36c1e6f905d4ea0de119451', 'yes'),
    (9, 'pharmacist', 'Petrovatest', 'Anna', 'Borisovna', '+37529222test', '66ebbdf8b3edc04d4bc74e8407b74b92', 'yes');



insert into pharm_group values 
	('cardiovascular'),
	('antibiotics'),
	('allergy'),
	('antipyretics'),
	('antiviral'),
	('antiseptics'),
	('immunity'),
	('nervous system'),
	('gastrointestinal'),
	('otolaryngology');



insert into drug values
	#cardiovascular
	(1, 'Mildronate', 'cardiovascular', 'capsules', '60 capsules', 'meldonium - 250 mg', 'Latvia', 'without prescription', 18.5, 50, 'yes'),
	(2, 'Reboksin', 'cardiovascular', 'pills', '30 pills', 'inosin - 200 mg', 'Belarus', 'without prescription', 3.15, 50, 'yes'),
	(3, 'Trental', 'cardiovascular', 'pills', '20 pills', 'pentoxifillin - 400 mg', 'Germany', 'on prescription', 21.7, 50, 'yes'),
	(4, 'Panangin', 'cardiovascular', 'pills', '50 pills', 'potassium asparaginate - 158 mg, magnesium asparaginate - 140 mg', 'Belarus', 'without prescription', 7.25, 50, 'yes'),
	(5, 'Analapril', 'cardiovascular', 'pills', '20 pills', 'analapril - 10 mg', 'Belarus', 'without prescription', 2, 50, 'yes'),
	(6, 'Nitroglicirin', 'cardiovascular', 'capsules', '50 capsules', 'nitroglicirin - 0.5 mg', 'Belarus', 'without prescription', 3, 50, 'yes'),
	(7, 'Carvalol', 'cardiovascular', 'drops', '30 ml', 'valeriana - 20%, fenobarbital - 18%, mint oil - 1.5%', 'Belarus',  'without prescription', 3, 50, 'yes'),
	(8, 'Validol', 'cardiovascular', 'pills', '20 pills', 'valeriana - 0.04 g, mint - 0.02 g', 'Belarus', 'without prescription', 2.3, 50, 'yes'),
	(9, 'Volocardin', 'cardiovascular', 'drops', '30 ml', 'fenobarbital - 18%, mint oil - 1.5%, hop oil - 1%', 'Belarus', 'without prescription', 2.1, 50, 'yes'),
	(10, 'Amlodipin', 'cardiovascular', 'pills', '20 pills', 'amlodipin - 5 mg', 'Belarus', 'without prescription', 3.5, 50, 'yes'),

	#antibiotics
    (11, 'Asitromicin', 'antibiotics', 'pills', '6 pills', 'asitromicin - 500 mg', 'Belarus', 'on prescription', 20, 50, 'yes'),
    (12, 'Supracs', 'antibiotics', 'suspension', '30 gramms', 'cefecsim - 100mg/5ml', 'Netherlands', 'on prescription', 25, 50, 'yes'),
	(13, 'Augmentin', 'antibiotics', 'pills', '14 pills', 'amocsicillin - 500 mg', 'Great Britain', 'without prescription', 12, 50, 'yes'),
	(14, 'Summamed', 'antibiotics', 'capsules', '6 capsules', 'asitromicin - 250 mg', 'Israel', 'on prescription', 22.8, 50, 'yes'),
	(15, 'Ciproflocsacin', 'antibiotics', 'pills', '20 pills', 'ciproflocsacin - 250 mg', 'Belarus', 'on prescription', 2.8, 50, 'yes'),
	(16, 'Ciproflocsacin', 'antibiotics', 'pills', '20 pills', 'ciproflocsacin - 500 mg', 'Belarus', 'on prescription', 3.4, 50, 'yes'),
	(17, 'Unidocs', 'antibiotics', 'pills', '10 pills', 'diocsicilin - 100 mg', 'Netherlands', 'on prescription', 15, 50, 'yes'),
	(18, 'Oflocsocin', 'antibiotics', 'pills', '10 pills', 'oflocsocin - 200 mg', 'Belarus', 'on prescription', 11, 50, 'yes'),
	(19, 'Amocsiclav', 'antibiotics', 'pills', '10 pills', 'amocsicillin - 300 mg', 'Belarus', 'without prescription', 10, 50, 'yes'),
    (20, 'Amocsicillin', 'antibiotics', 'capsules', '16 capsules', 'amocsicillin - 500 mg', 'Belarus', 'without prescription', 4.2, 50, 'yes'),

    #allergy
	(21, 'Suprastin', 'allergy', 'pills', '20 pills', 'chloropiramin - 25 mg', 'Hungary', 'without prescription', 3.55, 50, 'yes'),
    (22, 'Allercaps', 'allergy', 'capsules', '20 capsules', 'citrin - 5 mg', 'Belarus', 'without prescription', 1.83, 50, 'yes'),
	(23, 'Zodak', 'allergy', 'drops', '20 ml', 'citrin - 10 mg', 'Russia', 'without prescription', 5.55, 50, 'yes'),
	(24, 'Clarestin', 'allergy', 'pills', '20 pills', 'loratadin - 20 mg', 'Palestina', 'without prescription', 6.7, 50, 'yes'),
	(25, 'Loratadin', 'allergy', 'pills', '10 pills', 'loratadin - 10 mg', 'Romania', 'without prescription', 2.8, 50, 'yes'),

	#antipyretics
    (26, 'Paracetamol', 'antipyretics', 'pills', '10 pills', 'paracetamol - 500 mg', 'Belarus', 'without prescription', 1, 50, 'yes'),
	(27, 'Ibuclin', 'antipyretics', 'pills', '20 pills', 'paracetamol - 225 mg, ibuprofen - 250 mg', 'Latvia', 'without prescription', 3.5, 50, 'yes'),
	(28, 'Rinza', 'antipyretics', 'pills', '10 pills', 'paracetamol - 500 mg, caffeine - 30 mg', 'Belarus', 'without prescription', 3.49, 50, 'yes'),
	(29, 'Analgin', 'antipyretics', 'pills', '10 pills', 'analgin - 500 mg', 'Belarus', 'without prescription', 4.5, 50, 'yes'),
	(30, 'Teraflu', 'antipyretics', 'powder', '5 g', 'paracetamol - 325 mg, ascorbic acid - 50 mg', 'Belarus', 'without prescription', 1, 50, 'yes'),

	#antiviral
	(31, 'Amicsin', 'antiviral', 'pills', '20 pills', 'tyloron - 125 mg', 'Russia', 'without prescription', 32, 50, 'yes'),
	(32, 'Groprinosin', 'antiviral', 'pills', '50 pills', 'inosin parobecs - 500 mg', 'Hungary', 'without prescription', 32, 50, 'yes'),
	(33, 'Angrimacs', 'antiviral', 'capsules', '20 capsules', 'paracetamol - 180 mg, remantadin - 25 mg, ascorbic acid - 150 mg, loratadin - 1.5 mg', 'Belarus', 'without prescription', 3.25, 50, 'yes'),
	(34, 'Anaferon', 'antiviral', 'pills', '20 pills', 'interferon - 0.003 g', 'Russia', 'without prescription', 7, 50, 'yes'),
	(35, 'Arbidol', 'antiviral', 'pills', '20 pills', 'arbidol - 100 mg', 'Russia', 'without prescription', 12, 50, 'yes'),

	#antiseptics
	(36, 'Ethanol', 'antiseptics', 'solution', '100 ml', 'ethanol - 70%', 'Belarus', 'without prescription', 5, 50, 'yes'),
	(37, 'Hydrogen peroxide', 'antiseptics', 'solution', '100 ml', 'Hydrogen peroxide - 3 g', 'Belarus', 'without prescription', 1, 50, 'yes'),
	(38, 'Brilliant green', 'antiseptics', 'solution', '30 ml', 'tetroethil - 2%', 'Belarus', 'without prescription', 1.2, 50, 'yes'),
	(39, 'Iodine', 'antiseptics', 'solution', '30 ml', 'iodine - 5%', 'Belarus', 'without prescription', 1.5, 50, 'yes'),
	(40, 'Chlorhexidine', 'antiseptics', 'solution', '100 ml', 'chlorine - 0.05%', 'Belarus', 'without prescription', 2, 50, 'yes'),

	#immunity
	(41, 'Kagocel', 'immunity', 'pills', '10 pills', 'cagocel - 12 mg', 'Russia', 'without prescription', 7.28, 50, 'yes'),
	(42, 'Immunal', 'immunity', 'drops', '50 ml', 'echinacea juice - 80%, ethanol - 20%', 'Austria', 'without prescription', 5.5, 50, 'yes'),
	(43, 'Immunal', 'immunity', 'pills', '20 pills', 'echinacea juice - 80%', 'Slovenia', 'without prescription', 5, 50, 'yes'),
	(44, 'Cicloferon', 'immunity', 'solution', '5 ampoules of 5ml each', 'acridonusuenoic acid - 250 mg', 'Russia', 'without prescription', 4.5, 50, 'yes'),

	#nervous system
	(45, 'Adaptol', 'nervous system', 'pills', '20 pills', 'medicar - 500 mg', 'Latvia', 'without prescription', 24.2, 50, 'yes'),
	(46, 'Valeriana', 'nervous system', 'drops', '50 ml', 'valeriana - 20%, ethanol - 80%', 'Belarus', 'without prescription', 1.5, 50, 'yes'),
	(47, 'Valeriana brown', 'nervous system', 'pills', '20 pills', 'valeriana - 5 mg', 'Belarus', 'without prescription', 1.8, 50, 'yes'),
	(48, 'Valeriana yellow', 'nervous system', 'pills', '20 pills', 'valeriana - 2 mg', 'Belarus', 'without prescription', 1, 50, 'yes'),
	(49, 'Afobazol', 'nervous system', 'pills', '20 pills', 'fabomatizol - 10 mg', 'Russia', 'without prescription', 3.5, 50, 'yes'),
	(50, 'Persen', 'nervous system', 'pills', '30 pills', 'dry extract of melissa leaves - 35 mg', 'Slovenia', 'without prescription', 4.5, 50, 'yes'),
	(51, 'Novopassit', 'nervous system', 'drops', '50 ml', 'extract(mint, valeriana, hop, hawthorn, tutsan) - 40%', 'Latvia', 'without prescription', 8.5, 50, 'yes'),
	(52, 'Sedavit', 'nervous system', 'pills', '20 pills', 'dry extract(mint, valeriana, hop, hawthorn, tutsan, motherwort) - 50 mg', 'Belarus', 'without prescription', 7, 50, 'yes'),

	#gastrointestinal
	(53, 'Kreon', 'gastrointestinal', 'capsules', '20 capsules', 'pankreatin - 300 mg', 'Germany', 'without prescription', 20.18, 50, 'yes'),
	(54, 'Pankreatin', 'gastrointestinal', 'pills', '20 pills', 'pankreatin - 250 mg', 'Belarus', 'without prescription', 3.2, 50, 'yes'),
	(55, 'Stopdiar', 'gastrointestinal', 'pills', '24 pills', 'nifurocsazid - 100 mg', 'Hungary', 'without prescription', 9, 50, 'yes'),
	(56, 'Loperamid', 'gastrointestinal', 'capsules', '20 capsules', 'loperamid gidrochlorid - 2 mg', 'Belarus', 'without prescription', 1, 50, 'yes'),
	(57, 'Mezim forte 10000', 'gastrointestinal', 'pills', '20 pills', 'pankreatin - 125 mg', 'Germany', 'without prescription', 3.8, 50, 'yes'),
	(58, 'Activated carbon', 'gastrointestinal', 'pills', '10 pills', 'activated carbon - 2 part, magnium oxide - 1 part, tanin - 1 part', 'Belarus', 'without prescription', 0.72, 50, 'yes'),

	#otolaryngology
	(59, 'Kcilin', 'otolaryngology', 'drops', '10 ml', 'csilometazolin - 10%', 'Belarus', 'without prescription', 2.7, 50, 'yes'),
	(60, 'Kvics','otolaryngology', 'drops', '20 ml', 'clean ocean water', 'Germany', 'without prescription', 5.49, 50, 'yes'),
	(61, 'Pertusin', 'otolaryngology', 'syrup', '125 ml', 'thyme extract - 20%', 'Belarus', 'without prescription', 3.49, 50, 'yes'),
	(62, 'Sofradex', 'otolaryngology', 'drops', '5 ml', 'framecitina sulfate - 5 mg, gramicidin - 0.05 mg', 'Hungary', 'without prescription', 12, 50, 'yes'),
	(63, 'Ingalipt', 'otolaryngology', 'spray', '35 ml', 'extract(mint, sage, chamomile, eucalyptus) - 30%', 'Belarus', 'without prescription', 4, 50, 'yes');

	
	
insert into recipe values   
	('44441111', 1, 1, 3, 1, '2017-03-25 13:46:46', '2017-09-25 13:46:46', 'open'),
	('44442222', 1, 2, 11, 2, '2017-03-25 15:46:46', '2017-09-25 15:46:46', 'open'), 
	('44443333', 1, 3, 14, 1, '2017-03-25 16:46:46', '2017-09-25 16:46:46', 'open'),
	('44444444', 1, 3, 17, 1, '2017-03-25 16:46:46', '2017-09-25 16:46:46', 'open'),
	('55551111', 2, 4, 18, 1, '2017-03-25 13:46:46', '2017-09-25 13:46:46', 'open');



insert into account values
	(1, 5, 1000),
	(2, 6, 1000),
	(3, 7, 1000),
	(4, 8, 1000);