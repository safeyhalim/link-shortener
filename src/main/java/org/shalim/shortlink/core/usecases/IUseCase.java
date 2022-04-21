package org.shalim.shortlink.core.usecases;

public interface IUseCase <I extends IUseCase.Inputs, O extends IUseCase.Outputs> {
	public O execute(I input);
	public interface Inputs {}
	public interface Outputs {}
}
