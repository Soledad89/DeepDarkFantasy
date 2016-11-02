package com.thoughtworks.DDF.Language

import scalaz.Isomorphism._

trait IsoLang[OInfo[_], NInfo[_], ORepr[_], NRepr[_]] extends Lang[NInfo, NRepr] {
  def infoIso: OInfo <~> NInfo

  def reprIso: ORepr <~> NRepr

  def l: Lang[OInfo, ORepr]

  def rconv[A]: ORepr[A] => NRepr[A] = reprIso.to.apply[A]

  def convr[A]: NRepr[A] => ORepr[A] = reprIso.from.apply[A]

  def iconv[A]: OInfo[A] => NInfo[A] = infoIso.to.apply[A]

  implicit def convi[A](implicit n: NInfo[A]): OInfo[A] = infoIso.from(n)

  override def scanRight[A, B](implicit ai: NInfo[A], bi: NInfo[B]) = rconv(l.scanRight[A, B])

  override def zro[A, B](implicit ai: NInfo[A], bi: NInfo[B]) = rconv(l.zro[A, B])

  override def right[A, B](implicit ai: NInfo[A], bi: NInfo[B]) = rconv(l.right[A, B])

  override def ltD = rconv(l.ltD)

  override def contRet[R, A](implicit ri: NInfo[R], ai: NInfo[A]) = rconv(l.contRet[R, A])

  override def scanLeft[A, B](implicit ai: NInfo[A], bi: NInfo[B]) = rconv(l.scanLeft[A, B])

  override def listZip[A, B](implicit ai: NInfo[A], bi: NInfo[B]) = rconv(l.listZip[A, B])

  override def divD = rconv(l.divD)

  override def foldRight[A, B](implicit ai: NInfo[A], bi: NInfo[B]) = rconv(l.foldRight[A, B])

  override def none[A](implicit ai: NInfo[A]) = rconv(l.none[A])

  override def some[A](implicit ai: NInfo[A]) = rconv(l.some[A])

  override def optionMatch[A, B](implicit ai: NInfo[A], bi: NInfo[B]) = rconv(l.optionMatch[A, B])

  override def exceptBind[A, B, C](implicit ai: NInfo[A], bi: NInfo[B], ci: NInfo[C]) = rconv(l.exceptBind[A, B, C])

  override def reverse[A](implicit ai: NInfo[A]) = rconv(l.reverse[A])

  override def cons[A](implicit ai: NInfo[A]) = rconv(l.cons[A])

  override def app[A, B]: NRepr[A => B] => NRepr[A] => NRepr[B] = f => x => rconv(l.app(convr(f))(convr(x)))

  override implicit def optionInfo[A](implicit ai: NInfo[A]) = iconv(l.optionInfo[A])

  override def optionElmInfo[A] = oi => iconv(l.optionElmInfo[A](convi(oi)))

  override def listMap[A, B](implicit ai: NInfo[A], bi: NInfo[B]) = rconv(l.listMap[A, B])

  override def expD = rconv(l.expD)

  override def sumComm[A, B](implicit ai: NInfo[A], bi: NInfo[B]) = rconv(l.sumComm[A, B])

  override def C[A, B, C](implicit ai: NInfo[A], bi: NInfo[B], ci: NInfo[C]) = rconv(l.C[A, B, C])

  override def App[A, B](implicit ai: NInfo[A], bi: NInfo[B]) = rconv(l.App[A, B])

  override def uncurry[A, B, C](implicit ai: NInfo[A], bi: NInfo[B], ci: NInfo[C]) = rconv(l.uncurry[A, B, C])

  override def I[A](implicit ai: NInfo[A]) = rconv(l.I[A])

  override implicit def aInfo[A, B](implicit ai: NInfo[A], bi: NInfo[B]) = iconv(l.aInfo[A, B])

  override def domInfo[A, B] = ai => iconv(l.domInfo(convi(ai)))

  override def rngInfo[A, B] = ai => iconv(l.rngInfo(convi(ai)))

  override implicit def topInfo = iconv(l.topInfo)

  override def foldLeft[A, B](implicit ai: NInfo[A], bi: NInfo[B]) = rconv(l.foldLeft[A, B])

  override def mkUnit: NRepr[Unit] = rconv(l.mkUnit)

  override implicit def doubleInfo = iconv(l.doubleInfo)

  override def sumAssocRL[A, B, C](implicit ai: NInfo[A], bi: NInfo[B], ci: NInfo[C]) = rconv(l.sumAssocRL[A, B, C])

  override def fst[A, B](implicit ai: NInfo[A], bi: NInfo[B]) = rconv(l.fst[A, B])

  override def litB: (Boolean) => NRepr[Boolean] = ???

  override implicit def sumInfo[A, B](implicit ai: NInfo[A], bi: NInfo[B]): NInfo[Either[A, B]] = ???

  override def sumLeftInfo[A, B]: (NInfo[Either[A, B]]) => NInfo[A] = ???

  override def sumRightInfo[A, B]: (NInfo[Either[A, B]]) => NInfo[B] = ???

  override def Y[A, B](implicit ai: NInfo[A], bi: NInfo[B]): NRepr[(((A) => B) => (A) => B) => (A) => B] = ???

  override def Let[A, B](implicit ai: NInfo[A], bi: NInfo[B]): NRepr[(A) => ((A) => B) => B] = ???

  override def nil[A](implicit ai: NInfo[A]): NRepr[List[A]] = ???

  override def listMatch[A, B](implicit ai: NInfo[A], bi: NInfo[B]): NRepr[(List[A]) => (B) => ((A) => (List[A]) => B) => B] = ???

  override def W[A, B](implicit ai: NInfo[A], bi: NInfo[B]): NRepr[((A) => (A) => B) => (A) => B] = ???

  override def sumMatch[A, B, C](implicit ai: NInfo[A], bi: NInfo[B], ci: NInfo[C]): NRepr[(Either[A, B]) => ((A) => C) => ((B) => C) => C] = ???

  override def ite[A](implicit ai: NInfo[A]): NRepr[(Boolean) => (A) => (A) => A] = ???

  override def sigD: NRepr[(Double) => Double] = ???

  override def plusD: NRepr[(Double) => (Double) => Double] = ???

  override def multD: NRepr[(Double) => (Double) => Double] = ???

  override def K[A, B](implicit ai: NInfo[A], bi: NInfo[B]): NRepr[(A) => (B) => A] = ???

  override def curry[A, B, C](implicit ai: NInfo[A], bi: NInfo[B], ci: NInfo[C]): NRepr[(((A, B)) => C) => (A) => (B) => C] = ???

  override def left[A, B](implicit ai: NInfo[A], bi: NInfo[B]): NRepr[(A) => Either[A, B]] = ???

  override def S[A, B, C](implicit ai: NInfo[A], bi: NInfo[B], ci: NInfo[C]): NRepr[((A) => (B) => C) => ((A) => B) => (A) => C] = ???

  override implicit def prodInfo[A, B](implicit ai: NInfo[A], bi: NInfo[B]): NInfo[(A, B)] = ???

  override def prodZroInfo[A, B]: (NInfo[(A, B)]) => NInfo[A] = ???

  override def prodFstInfo[A, B]: (NInfo[(A, B)]) => NInfo[B] = ???

  override def sumAssocLR[A, B, C](implicit ai: NInfo[A], bi: NInfo[B], ci: NInfo[C]): NRepr[(Either[Either[A, B], C]) => Either[A, Either[B, C]]] = ???

  override def B[A, B, C](implicit ai: NInfo[A], bi: NInfo[B], ci: NInfo[C]): NRepr[((B) => C) => ((A) => B) => (A) => C] = ???

  override implicit def boolInfo: NInfo[Boolean] = ???

  override implicit def listInfo[A](implicit ai: NInfo[A]): NInfo[List[A]] = ???

  override def listElmInfo[A]: (NInfo[List[A]]) => NInfo[A] = ???

  override def litD: (Double) => NRepr[Double] = ???

  override def contBind[R, A, B](implicit ri: NInfo[R], ai: NInfo[A], bi: NInfo[B]):
  NRepr[(Cont[R, A]) => ((A) => Cont[R, B]) => Cont[R, B]] = ???

  override def mkProduct[A, B](implicit ai: NInfo[A], bi: NInfo[B]): NRepr[(A) => (B) => (A, B)] = ???

  override def reprInfo[A]: NRepr[A] => NInfo[A] = ???

  override def exfalso[A](implicit ai: NInfo[A]): NRepr[Nothing => A] = ???

  override implicit def botInfo: NInfo[Nothing] = ???

  override def imfalso[A](implicit ai: NInfo[A]): NRepr[Unit => A] = ???

  override def impossible: NRepr[Unit => Nothing] = ???
}

object IsoLang {
  def apply[OInfo[_], NInfo[_], ORepr[_], NRepr[_]]: IsoLang[OInfo, NInfo, ORepr, NRepr] =
    new IsoLang[OInfo, NInfo, ORepr, NRepr] {
      override def infoIso: OInfo <~> NInfo = ???

      override def reprIso: ORepr <~> NRepr = ???

      override def l: Lang[OInfo, ORepr] = ???
    }
}
